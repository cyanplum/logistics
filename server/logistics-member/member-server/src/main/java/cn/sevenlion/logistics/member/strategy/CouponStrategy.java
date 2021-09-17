package cn.sevenlion.logistics.member.strategy;

import cn.hutool.core.util.ObjectUtil;
import cn.sevenlion.logistics.common.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import cn.sevenlion.logistics.member.service.CouponStrategyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: qimeiwen
 * @create: 2021-09-17
 * 卡券策略
 */
@Slf4j
@Component
public class CouponStrategy {

    private Map<Integer, CouponStrategyService> serviceMap = new HashMap<>();

    public CouponStrategy(List<CouponStrategyService> couponStrategyServiceList) {
        couponStrategyServiceList.forEach(it -> serviceMap.put(it.type(), it));
    }

    public CouponStrategyService getService(Integer type) {
        CouponStrategyService couponStrategyService = serviceMap.get(type);
        if (ObjectUtil.isNull(couponStrategyService)) {
            throw new BaseException("获取卡券策略失败！");
        }
        return couponStrategyService;
    }
}
