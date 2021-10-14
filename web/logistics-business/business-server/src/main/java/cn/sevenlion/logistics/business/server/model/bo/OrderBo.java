package cn.sevenlion.logistics.business.server.model.bo;

import cn.sevenlion.logistics.common.model.json.AddressJsonObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author: qimeiwen
 * @create: 2021-09-11
 */
@Data
@ApiModel(value = "OrderBo", description = "下单对象")
public class OrderBo {

    @ApiModelProperty(value = "发送地址")
    @NotNull(message = "发送地址不能为空")
    private AddressJsonObject sendAddress;

    @ApiModelProperty(value = "接收地址")
    @NotNull(message = "接收地址不能为空")
    private AddressJsonObject receiveAddress;

    @ApiModelProperty(value = "邮寄名称")
    @NotBlank(message = "邮寄名称不能为空")
    private String mailName;

    @ApiModelProperty(value = "邮寄类型 快寄、慢寄")
    @NotNull(message = "邮寄类型不能为空")
    private Integer mailType;

    @ApiModelProperty(value = "邮寄物品类型")
    @NotNull(message = "邮寄物品类型不能为空")
    private Integer itemType;

    @ApiModelProperty(value = "邮寄物品数量 一共有多少件物品")
    @NotNull(message = "邮寄物品数量不能为空")
    private Integer itemNums;

    @ApiModelProperty(value = "重量")
    @NotNull(message = "重量不能为空")
    private Integer weight;

    @ApiModelProperty(value = "服务类型 如上门邮寄、到店邮寄")
    @NotNull(message = "服务类型不能为空")
    private Integer mailServiceType;

    @ApiModelProperty(value = "服务时间")
    private LocalDateTime mailServiceTime;

    @ApiModelProperty(value = "支付类型 货到付款")
    @NotNull(message = "支付类型不能为空")
    private Integer payType;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "交付时间")
    @NotNull(message = "交付时间不能为空")
    private LocalDateTime deliveryTime;


}
