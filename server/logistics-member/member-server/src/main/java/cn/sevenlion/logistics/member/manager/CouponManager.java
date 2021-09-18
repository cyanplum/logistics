package cn.sevenlion.logistics.member.manager;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.sevenlion.logistics.common.consts.RedisConst;
import cn.sevenlion.logistics.common.mapper.member.CouponBatchMapper;
import cn.sevenlion.logistics.common.mapper.member.CouponMapper;
import cn.sevenlion.logistics.common.model.entity.member.CouponBatchEntity;
import cn.sevenlion.logistics.common.model.entity.member.CouponEntity;
import cn.sevenlion.logistics.common.model.mogon.CouponBindEntity;
import cn.sevenlion.logistics.common.util.ObjectMapperUtil;
import cn.sevenlion.logistics.common.util.PageUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: qimeiwen
 * @create: 2021-09-17
 */
@Slf4j
@Component
public class CouponManager {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private CouponBatchMapper couponBatchMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    public Page<CouponBindEntity> selectBindCouponPageByUser(Integer pn, Integer size, String userCode, Integer status) {
        Criteria criteria = new Criteria();
        if (ObjectUtil.isNotNull(status)) {
            criteria.and("status").is(status);
        }
        if (StrUtil.isNotBlank(userCode)) {
            criteria.is("userCode").is(userCode);
        }
        Query query = new Query(criteria);

        long count = mongoTemplate.count(query, CouponBindEntity.class, CouponBindEntity.buildTableName(userCode));
        query.skip(size * (pn - 1));
        query.limit(size);
        List<CouponBindEntity> couponBindEntityList = mongoTemplate.find(query, CouponBindEntity.class, CouponBindEntity.buildTableName(userCode));
        Page<CouponBindEntity> page = PageUtil.buildPage(couponBindEntityList, count, pn, size);
        return page;
    }

    public CouponBindEntity selectBindCouponByByUser(String userCode, String serialCode) {
        Criteria criteria = new Criteria();
        if (ObjectUtil.isNotNull(serialCode)) {
            criteria.and("serialCode").is(serialCode);
        }
        if (StrUtil.isNotBlank(userCode)) {
            criteria.is("userCode").is(userCode);
        }
        Query query = new Query(criteria);
        CouponBindEntity couponBindEntity = mongoTemplate.findOne(query, CouponBindEntity.class, CouponBindEntity.buildTableName(userCode));
        return couponBindEntity;
    }

    public CouponEntity selectCouponById(String couponCode) {
        CouponEntity couponEntity = null;
        try {
            couponEntity = (CouponEntity)redisTemplate.opsForHash().get(RedisConst.COUPON_MAP, couponCode);
        } catch (Exception e) {
            log.warn("缓存获取卡券失败！原因:{}", e.getMessage());
            couponEntity = couponMapper.selectById(couponCode);
            if (ObjectUtil.isNotNull(couponEntity)) {
                try {
                    redisTemplate.opsForHash().put(RedisConst.COUPON_MAP, couponEntity.getSerialCode(), couponEntity);
                } catch (Exception ex) {
                    log.warn("缓存卡券失败！原因:{} 数据:{}", e.getMessage(), ObjectMapperUtil.obj2Str(couponEntity));
                }
            }
        }
        return couponEntity;
    }


    public CouponBatchEntity selectCouponBatch(String couponCode, String batchCode) {
        LambdaQueryWrapper<CouponBatchEntity> wrapper = new QueryWrapper<CouponBatchEntity>().lambda()
                .eq(StrUtil.isNotBlank(couponCode), CouponBatchEntity::getCouponCode, couponCode)
                .eq(StrUtil.isNotBlank(batchCode), CouponBatchEntity::getSerialCode, batchCode)
                .last("limit 1");
        return couponBatchMapper.selectOne(wrapper);
    }

    public CouponBatchEntity selectCouponBatchForUpdate(String couponCode, String batchCode) {
        return couponBatchMapper.selectCouponBatchForUpdate(couponCode, batchCode);
    }

    public int cutStock(String batchCode, int count) {
        return couponBatchMapper.cutStock(batchCode, count);
    }


    public IPage<CouponBatchEntity> selectCouponBatchPage(Integer pn, Integer size, String name, Integer type) {
        IPage<CouponBatchEntity> couponBatchEntityIPage = couponBatchMapper.selectCouponBatchPage(new Page<CouponBatchEntity>(pn, size), name, type);
        return couponBatchEntityIPage;
    }

    public List<CouponEntity> selectCouponListByCodeList(List<String> couponCodeList) {
        List<CouponEntity> couponEntityList = null;
        try {
            List<Object> values = redisTemplate.opsForHash().values(RedisConst.COUPON_MAP);
            couponEntityList = ObjectMapperUtil.getObjectMapper().convertValue(values, new TypeReference<List<CouponEntity>>() {});
        } catch (Exception e) {
            log.warn("缓存获取卡券列表失败！原因:{}", e.getMessage());
            couponEntityList = couponMapper.selectList(new QueryWrapper<CouponEntity>().lambda()
                    .in(CouponEntity::getSerialCode, couponCodeList));
            if (CollUtil.isNotEmpty(couponEntityList)) {
                try {
                    Map<String, CouponEntity> couponEntityMap = couponEntityList.stream().collect(Collectors.toMap(CouponEntity::getSerialCode, Function.identity()));
                    redisTemplate.opsForHash().putAll(RedisConst.COUPON_MAP, couponEntityMap);
                } catch (Exception ex) {
                    log.warn("缓存卡券失败！原因:{} 卡券code:{}", ex.getMessage(), ObjectMapperUtil.obj2Str(couponCodeList));
                }
            }
        }
        return couponEntityList;
    }

    public CouponBatchEntity selectCouponByBatchId(String batchCode) {
        return couponBatchMapper.selectById(batchCode);
    }
}
