package cn.sevenlion.logistics.business.server.service.impl;

import cn.sevenlion.logistics.business.server.service.IOrderFreightRelationService;
import cn.sevenlion.logistics.common.mapper.business.OrderFreightRelationMapper;
import cn.sevenlion.logistics.common.model.entity.business.OrderFreightEntity;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单货物批次表 服务实现类
 * </p>
 *
 * @author cyanplum
 * @since 2021-09-11
 */
@Service
public class OrderFreightRelationServiceImpl extends ServiceImpl<OrderFreightRelationMapper, OrderFreightEntity> implements IOrderFreightRelationService {

}
