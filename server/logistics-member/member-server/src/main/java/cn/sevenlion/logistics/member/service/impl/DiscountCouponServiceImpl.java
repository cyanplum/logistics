package cn.sevenlion.logistics.member.service.impl;

import cn.sevenlion.logistics.common.model.enums.CouponTypeEnum;
import org.springframework.stereotype.Service;
import cn.sevenlion.logistics.member.service.CouponStrategyService;

/**
 * @author: qimeiwen
 * @create: 2021-09-17
 */
@Service
public class DiscountCouponServiceImpl implements CouponStrategyService {

    @Override
    public Integer type() {
        return CouponTypeEnum.DISCOUNT.getCode();
    }
}
