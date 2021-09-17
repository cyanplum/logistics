package cn.sevenlion.logistics.member.service.impl;

import cn.sevenlion.logistics.common.exception.BaseException;
import cn.sevenlion.logistics.common.model.entity.member.CouponEntity;
import cn.sevenlion.logistics.common.model.entity.member.GrowthLevelEntity;
import cn.sevenlion.logistics.common.model.entity.user.UserEntity;
import cn.sevenlion.logistics.common.model.enums.CouponExchangeTypeEnum;
import cn.sevenlion.logistics.member.manager.GrowthLevelManager;
import cn.sevenlion.logistics.member.service.ExchangeStrategyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: qimeiwen
 * @create: 2021-09-17
 */
@Slf4j
@Service
public class IntegralExchangeServiceImpl implements ExchangeStrategyService {

    @Autowired
    GrowthLevelManager growthLevelManager;


    @Override
    public Integer type() {
        return CouponExchangeTypeEnum.INTEGRAL.getCode();
    }

    @Override
    public void checkDeserve(UserEntity userEntity, CouponEntity couponEntity) {
        GrowthLevelEntity growthLevelEntity = growthLevelManager.selectUserGrowthLevel(userEntity);
        if (growthLevelEntity.getGrade() < couponEntity.getMinGrade() || growthLevelEntity.getGrade() > couponEntity.getMaxGrade()) {
            throw new BaseException("等级不符合要求！");
        }
        if (userEntity.getIntegral() < couponEntity.getExchangeLimit()) {
            throw new BaseException("积分不足！");
        }
    }

    @Override
    public boolean exchange(UserEntity userEntity, CouponEntity couponEntity) {
        // TODO: 2021/9/17 领取
        return false;
    }
}
