package cn.sevenlion.logistics.common.model.entity.member;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 卡券表
 * </p>
 *
 * @author cyanplum
 * @since 2021-09-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("member_coupon")
@ApiModel(value="MemberCouponEntity对象", description="卡券表")
public class CouponEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(value = "id")
    private Long id;

    @ApiModelProperty(value = "唯一编码")
    @TableId("serial_code")
    private String serialCode;

    @ApiModelProperty(value = "名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "卡券类型")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "兑换方式")
    @TableField("exchange_type")
    private Integer exchangeType;

    @ApiModelProperty(value = "卡券使用范围")
    @TableField("scope")
    private Integer scope;

    @ApiModelProperty("兑换限制")
    @TableField("exchange_limit")
    private Integer exchangeLimit;

    @ApiModelProperty(value = "卡券值")
    @TableField("value")
    private Integer value;

    @ApiModelProperty(value = "最低使用额度")
    @TableField("minimum")
    private Integer minimum;

    @ApiModelProperty(value = "描述")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "最小等级")
    @TableField("min_grade")
    private Integer minGrade;

    @ApiModelProperty(value = "最大等级")
    @TableField("max_grade")
    private Integer maxGrade;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "逻辑删除")
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;


}
