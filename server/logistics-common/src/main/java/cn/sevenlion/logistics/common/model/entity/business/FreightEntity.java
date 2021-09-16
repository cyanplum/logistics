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
 * 货运信息表
 * </p>
 *
 * @author cyanplum
 * @since 2021-09-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("business_freight")
@ApiModel(value="FreightEntity对象", description="货运信息表")
public class FreightEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "货运编码")
    @TableField("serial_code")
    private String serialCode;

    @ApiModelProperty(value = "邮递员编号")
    @TableField("postman_code")
    private String postmanCode;

    @ApiModelProperty(value = "发送地址")
    @TableField("send_address")
    private String sendAddress;

    @ApiModelProperty(value = "接收地址")
    @TableField("receive_address")
    private String receiveAddress;

    @ApiModelProperty(value = "物流状态")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;


}
