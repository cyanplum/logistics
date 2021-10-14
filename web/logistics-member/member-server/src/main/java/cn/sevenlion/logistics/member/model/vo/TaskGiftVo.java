package cn.sevenlion.logistics.member.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: qimeiwen
 * @create: 2021-09-22
 */
@Data
@ApiModel(value = "TaskGiftVo", description = "任务礼物列表")
public class TaskGiftVo {

    private Integer id;

    @ApiModelProperty(value = "编号")
    private String serialCode;

    @ApiModelProperty(value = "任务编号")
    private String taskCode;

    @ApiModelProperty(value = "礼品类型")
    private Integer giftType;

    @ApiModelProperty(value = "礼品类型名称")
    private String giftTypeName;

    @ApiModelProperty(value = "礼品值")
    private String giftValue;

    @ApiModelProperty(value = "礼品限制条件")
    private String giftLimit;

    @ApiModelProperty(value = "可获得奖品最小vip等级")
    private Integer minVipGrade;

    @ApiModelProperty(value = "可参获得奖品最大vip等级 ")
    private Integer maxVipGrade;

    @ApiModelProperty(value = "周期数")
    private Integer giftCycle;
}
