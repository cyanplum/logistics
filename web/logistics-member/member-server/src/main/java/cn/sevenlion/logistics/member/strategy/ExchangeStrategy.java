package cn.sevenlion.logistics.member.strategy;

import cn.hutool.core.util.ObjectUtil;
import cn.sevenlion.logistics.common.exception.BaseException;
import cn.sevenlion.logistics.member.service.ExchangeStrategyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: qimeiwen
 * @create: 2021-09-17
 */
@Slf4j
@Component
public class ExchangeStrategy {

    Map<Integer, ExchangeStrategyService> serviceMap = new HashMap<>();

    public ExchangeStrategy(List<ExchangeStrategyService> exchangeStrategyServiceList) {
        exchangeStrategyServiceList.forEach(it -> serviceMap.put(it.type(), it));
    }

    public ExchangeStrategyService getService(Integer type) {
        ExchangeStrategyService exchangeStrategyService = serviceMap.get(type);
        if (ObjectUtil.isNull(exchangeStrategyService)) {
            throw new BaseException("卡券兑换策略获取失败！");
        }
        return exchangeStrategyService;
    }
}
