package cn.sevenlion.logistics.business.server.service;

import cn.sevenlion.logistics.business.common.model.entity.OrderEntity;
import cn.sevenlion.logistics.business.server.model.bo.OrderBo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author cyanplum
 * @since 2021-09-11
 */
public interface OrderService extends IService<OrderEntity> {

    /**
     * 提交订单
     * @param orderBo
     * @return
     */
    boolean submit(OrderBo orderBo);

    /**
     * 计算价格
     * @param orderBo
     * @return
     */
    BigDecimal calculate(OrderBo orderBo);
}
