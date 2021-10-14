package cn.sevenlion.logistics.member.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: qimeiwen
 * @create: 2021-09-18
 */
@Data
@ApiModel(value = "TaskVo", description = "任务返回列表")
public class TaskVo {

    private Integer id;

    @ApiModelProperty(value = "编号")
    private String serialCode;

    @ApiModelProperty(value = "任务名称")
    private String taskName;

    @ApiModelProperty(value = "任务的类型")
    private Integer type;

    @ApiModelProperty(value = "任务的类型名称")
    private String typeName;

    @ApiModelProperty(value = "可参与任务的最大vip等级")
    private Integer minVipGrade;

    @ApiModelProperty(value = "可参与任务的最大vip等级")
    private Integer maxVipGrade;

    @ApiModelProperty(value = "周期数")
    private Integer taskCycle;

    @ApiModelProperty(value = "权重")
    private Integer weight;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "任务列表")
    private List<TaskGiftVo> taskGiftVoList;

}
