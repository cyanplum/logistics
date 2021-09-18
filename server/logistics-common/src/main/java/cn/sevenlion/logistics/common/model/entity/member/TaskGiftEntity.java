package cn.sevenlion.logistics.common.model.entity.member;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 任务礼物表
 * </p>
 *
 * @author cyanplum
 * @since 2021-09-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("member_task_gift")
@ApiModel(value="MemberTaskGiftEntity对象", description="任务礼物表")
public class TaskGiftEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "编号")
    @TableField("serial_code")
    private String serialCode;

    @ApiModelProperty(value = "任务编号")
    @TableField("task_code")
    private String taskCode;

    @ApiModelProperty(value = "礼品类型")
    @TableField("gift_type")
    private Integer giftType;

    @ApiModelProperty(value = "礼品值")
    @TableField("gift_value")
    private String giftValue;

    @ApiModelProperty(value = "礼品限制条件")
    @TableField("gift_limit")
    private String giftLimit;

    @ApiModelProperty(value = "可获得奖品最小vip等级")
    @TableField("min_vip_grade")
    private Integer minVipGrade;

    @ApiModelProperty(value = "可参获得奖品最大vip等级 ")
    @TableField("max_vip_grade")
    private Integer maxVipGrade;

    @ApiModelProperty(value = "周期数")
    @TableField("gift_cycle")
    private Integer giftCycle;

    @ApiModelProperty(value = "启用状态")
    @TableField("status")
    private Boolean status;

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
