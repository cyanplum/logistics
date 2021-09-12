package cn.sevenlion.logistics.business.server.service.impl;

import cn.sevenlion.logistics.business.common.model.entity.OrderEntity;
import cn.sevenlion.logistics.business.server.mapper.OrderMapper;
import cn.sevenlion.logistics.business.server.model.bo.OrderBo;
import cn.sevenlion.logistics.business.server.service.OrderService;
import cn.sevenlion.logistics.common.enums.PayStatusEnum;
import cn.sevenlion.logistics.common.util.SerialCodeUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author cyanplum
 * @since 2021-09-11
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements OrderService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean submit(OrderBo orderBo) {
        // 1.计算价格
        BigDecimal price = calculate(orderBo);
        // 2.生成订单
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(orderBo, orderEntity);
        orderEntity.setSerialCode(SerialCodeUtils.generateSerialCode());
        // 3.调用微信
        // 4.入库 待支付状态
        orderEntity.setPayStatus(PayStatusEnum.WAITING.getStatus());
        return save(orderEntity);
    }

    @Override
    public BigDecimal calculate(OrderBo orderBo) {
        return null;
    }
}
