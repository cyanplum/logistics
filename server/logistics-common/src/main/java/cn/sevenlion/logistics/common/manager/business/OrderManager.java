package cn.sevenlion.logistics.common.manager.business;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.sevenlion.logistics.common.mapper.business.OrderMapper;
import cn.sevenlion.logistics.common.model.entity.business.OrderEntity;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: qimeiwen
 * @create: 2021-09-16
 */
@Component
public class OrderManager {

    @Autowired
    private OrderMapper orderMapper;

    public Page<OrderEntity> selectOrderPage(Integer pn, Integer size, String userCode, String orderNo, Integer payStatus) {
        LambdaQueryWrapper<OrderEntity> wrapper = new QueryWrapper<OrderEntity>().lambda()
                .eq(StrUtil.isNotBlank(userCode), OrderEntity::getUserCode, userCode)
                .eq(StrUtil.isNotBlank(orderNo), OrderEntity::getOrderNo, orderNo)
                .eq(ObjectUtil.isNotNull(payStatus), OrderEntity::getPayStatus, payStatus);
        Page<OrderEntity> orderEntityPage = orderMapper.selectPage(new Page<OrderEntity>(pn, size), wrapper);
        return orderEntityPage;
    }
}
