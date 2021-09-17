package cn.sevenlion.logistics.member.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.sevenlion.logistics.common.consts.RedisConst;
import cn.sevenlion.logistics.common.exception.BaseException;
import cn.sevenlion.logistics.common.mapper.member.CouponMapper;
import cn.sevenlion.logistics.common.model.entity.member.CouponBatchEntity;
import cn.sevenlion.logistics.common.model.entity.member.CouponEntity;
import cn.sevenlion.logistics.common.model.entity.user.UserEntity;
import cn.sevenlion.logistics.common.model.enums.CouponStatus;
import cn.sevenlion.logistics.common.model.mogon.CouponBindEntity;
import cn.sevenlion.logistics.common.util.ObjectMapperUtil;
import cn.sevenlion.logistics.common.util.PageUtil;
import cn.sevenlion.logistics.member.service.CouponStrategyService;
import cn.sevenlion.logistics.member.service.ExchangeStrategyService;
import cn.sevenlion.logistics.member.strategy.CouponStrategy;
import cn.sevenlion.logistics.member.strategy.ExchangeStrategy;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.sevenlion.logistics.member.manager.CouponManager;
import cn.sevenlion.logistics.member.manager.UserManager;
import cn.sevenlion.logistics.member.model.bo.CouponBindBo;
import cn.sevenlion.logistics.member.model.query.CouponQueryModel;
import cn.sevenlion.logistics.member.model.vo.CouponVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.sevenlion.logistics.member.service.CouponService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.stream.Collectors;

/**
 * @author: qimeiwen
 * @create: 2021-09-17
 */
@Slf4j
@Service
public class CouponServiceImpl extends ServiceImpl<CouponMapper, CouponEntity> implements CouponService {

    @Autowired
    private CouponManager couponManager;

    @Autowired
    private UserManager userManager;

    @Autowired
    private RedisLockRegistry redisLockRegistry;

    @Autowired
    private CouponStrategy couponStrategy;

    @Autowired
    private ExchangeStrategy exchangeStrategy;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Page<CouponVo> selectCouponPage(String userCode, CouponQueryModel queryModel) {
        Page<CouponBindEntity> couponBindEntityPage = couponManager.selectCouponBindPage(queryModel.getPn(), queryModel.getSize(), userCode, queryModel.getStatus());
        List<CouponVo> result = couponBindEntityPage.getRecords().stream().map(it -> {
            CouponVo couponVo = new CouponVo();
            BeanUtils.copyProperties(it, couponVo);
            return couponVo;
        }).collect(Collectors.toList());
        Page<CouponVo> page = PageUtil.buildPage(couponBindEntityPage, result);
        return page;
    }

    @Override
    public CouponVo selectCouponById(String userCode, String serialCode) {
        CouponBindEntity couponBindEntity = couponManager.selectCouponBindById(userCode, serialCode);
        if (ObjectUtil.isNull(couponBindEntity)) {
            throw new BaseException("卡券不存在！");
        }
        CouponVo couponVo = new CouponVo();
        BeanUtils.copyProperties(couponBindEntity, couponVo);
        return couponVo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean receiveCoupon(String userCode, CouponBindBo bo) {
        //查询用户积分
        UserEntity userEntity = userManager.selectUserById(userCode);
        if (ObjectUtil.isNull(userEntity)) {
            throw new BaseException("用户不存在！");
        }
        CouponEntity couponEntity = couponManager.selectCouponById(bo.getCouponCode());
        if (ObjectUtil.isNull(couponEntity)) {
            throw new BaseException("卡券不存在！");
        }
        //库存加锁查询
        CouponBatchEntity couponBatchEntity = couponManager.selectCouponBatchForUpdate(bo.getCouponCode(), bo.getBatchCode());
        if (ObjectUtil.isNull(couponBatchEntity)) {
            throw new BaseException("卡券批次不存在！");
        }
        //获取卡券兑换类型
        ExchangeStrategyService exchangeStrategyService = exchangeStrategy.getService(couponEntity.getExchangeType());
        //检查能否兑换
        exchangeStrategyService.checkDeserve(userEntity, couponEntity);
        String key = String.format(RedisConst.COUPON_BIND_KEY, couponBatchEntity.getSerialCode());
        Lock lock = redisLockRegistry.obtain(key);
        try {
            if (lock.tryLock(10L, TimeUnit.SECONDS)) {
                //扣减库存
                int success = couponManager.cutStock(couponBatchEntity.getSerialCode(), 1);
                if (success != 1) {
                    throw new BaseException("库存不足！");
                }
                //兑换
                boolean exchange = exchangeStrategyService.exchange(userEntity, couponEntity);
                if (!exchange) {
                    log.error("兑换卡券失败！账号:{} 卡券编号:{} 卡券批次:{}", userCode, bo.getCouponCode(), bo.getBatchCode());
                    throw new BaseException("兑换失败！");
                }
                CouponBindEntity couponBindEntity = buildBindEntity(userEntity, couponEntity, couponBatchEntity);
                mongoTemplate.insert(couponBindEntity,CouponBindEntity.buildTableName(userCode));
            }
        } catch (Exception e) {
            throw new BaseException(e.getMessage());
        } finally {
            lock.unlock();
        }
        return true;
    }

    private CouponBindEntity buildBindEntity(UserEntity userEntity, CouponEntity couponEntity, CouponBatchEntity batchEntity) {
        CouponBindEntity couponBindEntity = new CouponBindEntity();
        BeanUtils.copyProperties(couponBindEntity, couponEntity);
        couponBindEntity.setUserCode(userEntity.getSerialCode());
        couponBindEntity.setBindTime(LocalDateTime.now());
        couponBindEntity.setCouponCode(couponEntity.getSerialCode());
        couponBindEntity.setBatchCode(batchEntity.getSerialCode());
        couponBindEntity.setStatus(CouponStatus.WAITING.getCode());
        couponBindEntity.setDays(batchEntity.getDays());
        couponBindEntity.setStartTime(batchEntity.getStartTime());
        couponBindEntity.setExpireTime(batchEntity.getExpireTime());
        return couponBindEntity;
    }
}
