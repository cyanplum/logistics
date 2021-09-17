package cn.sevenlion.logistics.member.service.impl;

import cn.sevenlion.logistics.common.model.enums.CouponTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import cn.sevenlion.logistics.member.service.CouponStrategyService;

/**
 * @author: qimeiwen
 * @create: 2021-09-17
 */
@Slf4j
@Service
public class DeductionCouponServiceImpl implements CouponStrategyService {

    @Override
    public Integer type() {
        return CouponTypeEnum.DEDUCTION.getCode();
    }
}
