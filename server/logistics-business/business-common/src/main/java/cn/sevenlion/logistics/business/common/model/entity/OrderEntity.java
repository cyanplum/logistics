package cn.sevenlion.logistics.business.common.model.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author cyanplum
 * @since 2021-09-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("order")
@ApiModel(value="OrderEntity对象", description="订单表")
public class OrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "订单编码")
    @TableField("serial_code")
    private String serialCode;

    @ApiModelProperty(value = "发送地址")
    @TableField("send_address")
    private String sendAddress;

    @ApiModelProperty(value = "接收地址")
    @TableField("receive_address")
    private String receiveAddress;

    @ApiModelProperty(value = "邮寄名称")
    @TableField("mail_name")
    private String mailName;

    @ApiModelProperty(value = "邮寄类型 快寄、慢寄")
    @TableField("mail_type")
    private Integer mailType;

    @ApiModelProperty(value = "邮寄物品类型")
    @TableField("item_type")
    private Integer itemType;

    @ApiModelProperty(value = "邮寄物品数量 一共有多少件物品")
    @TableField("item_nums")
    private String itemNums;

    @ApiModelProperty(value = "重量")
    @TableField("weight")
    private Integer weight;

    @ApiModelProperty(value = "价格")
    @TableField("price")
    private BigDecimal price;

    @ApiModelProperty(value = "服务类型 如上门邮寄、到店邮寄")
    @TableField("service_type")
    private Integer serviceType;

    @ApiModelProperty(value = "服务时间")
    @TableField("service_time")
    private LocalDateTime serviceTime;

    @ApiModelProperty(value = "支付类型 货到付款")
    @TableField("pay_type")
    private Integer payType;

    @ApiModelProperty(value = "支付状态 已支付、未支付")
    @TableField("pay_status")
    private Integer payStatus;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "交付时间")
    @TableField("delivery_time")
    private LocalDateTime deliveryTime;

    @ApiModelProperty(value = "订单状态 整个订单的状态")
    @TableField("order_status")
    private Integer orderStatus;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;


}
