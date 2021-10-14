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
 * 任务表
 * </p>
 *
 * @author cyanplum
 * @since 2021-09-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("member_task")
@ApiModel(value="MemberTaskEntity对象", description="任务表")
public class TaskEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "编号")
    @TableField("serial_code")
    private String serialCode;

    @ApiModelProperty(value = "任务名称")
    @TableField("task_name")
    private String taskName;

    @ApiModelProperty(value = "任务的类型")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "可参与任务的最大vip等级")
    @TableField("min_vip_grade")
    private Integer minVipGrade;

    @ApiModelProperty(value = "可参与任务的最大vip等级")
    @TableField("max_vip_grade")
    private Integer maxVipGrade;

    @ApiModelProperty(value = "周期数")
    @TableField("task_cycle")
    private Integer taskCycle;

    @ApiModelProperty(value = "权重")
    @TableField("weight")
    private Integer weight;

    @ApiModelProperty(value = "描述")
    @TableField("description")
    private String description;

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
