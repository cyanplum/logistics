package cn.sevenlion.logistics.common.model.entity.member;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author cyanplum
 * @since 2021-09-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("member_coupon_batch")
@ApiModel(value="MemberCouponBatchEntity对象", description="")
public class CouponBatchEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "唯一编码")
    @TableField("serial_code")
    private String serialCode;

    @ApiModelProperty(value = "卡券编号")
    @TableField("coupon_code")
    private String couponCode;

    @ApiModelProperty(value = "有效使用天数")
    @TableField("days")
    private Integer days;

    @ApiModelProperty(value = "总量")
    @TableField("total")
    private Integer total;

    @ApiModelProperty(value = "已领取数量")
    @TableField("receiver_num")
    private Integer receiverNum;

    @ApiModelProperty(value = "使用数量")
    @TableField("use_num")
    private Integer useNum;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "卡券生效时间")
    @TableField("start_time")
    private LocalDate startTime;

    @ApiModelProperty(value = "卡券过期时间")
    @TableField("expire_time")
    private LocalDate expireTime;

    @ApiModelProperty(value = "逻辑删除")
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;


}
