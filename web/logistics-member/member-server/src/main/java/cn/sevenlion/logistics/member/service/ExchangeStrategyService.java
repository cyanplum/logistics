package cn.sevenlion.logistics.member.service;

import cn.sevenlion.logistics.common.model.entity.member.CouponEntity;
import cn.sevenlion.logistics.common.model.entity.user.UserEntity;

/**
 * @author: qimeiwen
 * @create: 2021-09-17
 */
public interface ExchangeStrategyService {

    /**
     * 获取兑换类型
     * @return
     */
    public Integer type();


    /**
     * 检查能否兑换
     * @param userEntity
     * @param couponEntity
     * @return
     */
    public void checkDeserve(UserEntity userEntity, CouponEntity couponEntity);

    /**
     * 兑换
     * @param userEntity
     * @param couponEntity
     * @return
     */
    public boolean exchange(UserEntity userEntity, CouponEntity couponEntity);
}
