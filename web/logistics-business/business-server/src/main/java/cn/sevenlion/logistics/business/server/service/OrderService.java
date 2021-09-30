package cn.sevenlion.logistics.business.server.service;

import cn.sevenlion.logistics.business.server.model.bo.OrderBo;
import cn.sevenlion.logistics.business.server.model.query.OrderQueryModel;
import cn.sevenlion.logistics.business.server.model.vo.OrderVo;
import cn.sevenlion.logistics.common.model.entity.business.OrderEntity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    /**
     * 查询订单列表
     * @param userCode
     * @param queryModel
     * @return
     */
    Page<OrderVo> selectOrderPage(String userCode, OrderQueryModel queryModel);

    /**
     * 根据主键id查询
     * @param userCode
     * @param serialCode
     * @return
     */
    OrderVo selectById(String userCode, String serialCode);
}
