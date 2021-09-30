package cn.sevenlion.logistics.common.model.entity.business;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单货物批次表
 * </p>
 *
 * @author cyanplum
 * @since 2021-09-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("business_order_freight")
@ApiModel(value="OrderFreightRelationEntity对象", description="订单货物批次表")
public class OrderFreightEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(value = "id")
    private Integer id;

    @ApiModelProperty(value = "唯一编码")
    @TableId("serial_code")
    private String serialCode;

    @ApiModelProperty(value = "货物批次code")
    @TableField("freight_code")
    private String freightCode;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;


}
