package cn.sevenlion.logistics.business.server.service.impl;

import cn.sevenlion.logistics.business.common.model.entity.OrderEntity;
import cn.sevenlion.logistics.business.server.mapper.OrderMapper;
import cn.sevenlion.logistics.business.server.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author cyanplum
 * @since 2021-09-11
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements IOrderService {

}
