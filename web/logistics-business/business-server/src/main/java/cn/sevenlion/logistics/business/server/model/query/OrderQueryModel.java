package cn.sevenlion.logistics.business.server.model.query;

import cn.sevenlion.logistics.common.model.query.BaseQueryModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: qimeiwen
 * @create: 2021-09-16
 */
@Data
@ApiModel(value = "OrderQueryModel", description = "订单查询对象")
public class OrderQueryModel extends BaseQueryModel {

    @ApiModelProperty("订单编号")
    private String orderNo;

    @ApiModelProperty("支付状态")
    private Integer payStatus;
}
