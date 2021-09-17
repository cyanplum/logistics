package cn.sevenlion.logistics.business.server.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.sevenlion.logistics.business.server.model.bo.OrderBo;
import cn.sevenlion.logistics.business.server.model.query.OrderQueryModel;
import cn.sevenlion.logistics.business.server.model.vo.OrderVo;
import cn.sevenlion.logistics.business.server.service.OrderService;
import cn.sevenlion.logistics.common.exception.BaseException;
import cn.sevenlion.logistics.business.server.manager.OrderManager;
import cn.sevenlion.logistics.common.mapper.business.OrderMapper;
import cn.sevenlion.logistics.common.model.entity.business.OrderEntity;
import cn.sevenlion.logistics.common.model.enums.PayStatusEnum;
import cn.sevenlion.logistics.common.util.PageUtil;
import cn.sevenlion.logistics.common.util.SerialCodeUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private OrderManager orderManager;

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
        orderEntity.setPayStatus(PayStatusEnum.WAITING.getCode());
        return save(orderEntity);
    }

    @Override
    public BigDecimal calculate(OrderBo orderBo) {
        return null;
    }

    @Override
    public Page<OrderVo> selectOrderPage(String userCode, OrderQueryModel queryModel) {

        Page<OrderEntity> orderEntityPage = orderManager.selectOrderPage(queryModel.getPn(), queryModel.getSize(), userCode, queryModel.getOrderNo(), queryModel.getPayStatus());
        if (orderEntityPage.getRecords().isEmpty()) {
            return new Page<>();
        }
        List<OrderVo> result = orderEntityPage.getRecords().stream().map(it -> {
            OrderVo vo = new OrderVo();
            BeanUtils.copyProperties(it, vo);
            return vo;
        }).collect(Collectors.toList());
        return PageUtil.buildPage(orderEntityPage, result);
    }

    @Override
    public OrderVo selectById(String userCode, String serialCode) {
        OrderEntity orderEntity = getById(serialCode);
        if (ObjectUtil.isNull(orderEntity) || !orderEntity.getUserCode().equals(userCode)) {
            throw new BaseException("订单不存在！");
        }
        OrderVo vo = new OrderVo();
        BeanUtils.copyProperties(orderEntity, vo);
        return vo;
    }
}
