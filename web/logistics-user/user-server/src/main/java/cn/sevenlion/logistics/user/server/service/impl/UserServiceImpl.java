package cn.sevenlion.logistics.user.server.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.sevenlion.logistics.common.consts.RedisConst;
import cn.sevenlion.logistics.common.exception.BaseException;
import cn.sevenlion.logistics.common.model.entity.member.CouponBatchEntity;
import cn.sevenlion.logistics.common.model.enums.CouponStatus;
import cn.sevenlion.logistics.common.model.mogon.CouponBindEntity;
import cn.sevenlion.logistics.user.server.mamager.CouponBindManager;
import cn.sevenlion.logistics.user.server.mamager.UserManager;
import cn.sevenlion.logistics.common.mapper.user.UserMapper;
import cn.sevenlion.logistics.common.model.entity.member.GrowthLevelEntity;
import cn.sevenlion.logistics.common.model.entity.user.UserEntity;
import cn.sevenlion.logistics.common.util.ObjectMapperUtil;
import cn.sevenlion.logistics.user.server.model.query.UserAuthQueryModel;
import cn.sevenlion.logistics.user.server.model.vo.UserDetailVo;
import cn.sevenlion.logistics.user.server.model.vo.UserVo;
import cn.sevenlion.logistics.user.server.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/9/10 10:27 下午
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserManager userManager;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private CouponBindManager couponBindManager;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserEntity auth(UserAuthQueryModel queryModel) {

        UserEntity userEntity = userMapper.selectOne(new QueryWrapper<UserEntity>().lambda()
                .eq(UserEntity::getUsername, queryModel.getUsername())
                .last("limit 1"));
        if (ObjectUtil.isNull(userEntity)) {
            throw new BaseException("用户名不存在！");
        }
        if (!passwordEncoder.matches(queryModel.getPassword(), userEntity.getPassword())) {
            throw new BaseException("密码不正确！");
        }
        return userEntity;
    }

    @Override
    public UserVo selectUser(String userCode) {
        //查询用户信息
        UserEntity userEntity = userManager.selectById(userCode);
        if (ObjectUtil.isNull(userEntity)) {
            throw new BaseException("用户不存在！");
        }
        //构建用户基本信息
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userEntity, userVo);
        //获取等级
        GrowthLevelEntity growthLevelEntity = userManager.selectUserGrowthLevel(userEntity);
        if (ObjectUtil.isNotNull(growthLevelEntity)) {
            userVo.setGradeIcon(growthLevelEntity.getIcon());
            userVo.setGradeName(growthLevelEntity.getName());
            userVo.setNextGradeGrowth(growthLevelEntity.getEndValue());
        }
        //查询未使用的优惠券的卡券
        Long couponCount = couponBindManager.selectCountByStatus(userCode, CouponStatus.WAITING.getCode());
        userVo.setCouponCount(couponCount);
        return userVo;
    }

    @Override
    public UserDetailVo selectDetailUser(String userCode) {
        //查询用户信息
        UserEntity userEntity = userManager.selectById(userCode);
        if (ObjectUtil.isNull(userEntity)) {
            throw new BaseException("用户不存在！");
        }
        //构建用户基本信息
        UserDetailVo userDetailVo = new UserDetailVo();
        BeanUtils.copyProperties(userEntity, userDetailVo);
        return userDetailVo;
    }
}
