package cn.sevenlion.logistics.user.server.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: qimeiwen
 * @create: 2021-09-17
 */
@Data
@ApiModel(value = "UserVo", description = "用户简单信息")
public class UserVo {

    @ApiModelProperty(value = "唯一编码")
    private String serialCode;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户状态")
    private Integer status;

    @ApiModelProperty(value = "用户电话")
    private String phone;

    @ApiModelProperty(value = "当前积分")
    private Integer integral;

    @ApiModelProperty(value = "历史总计获得积分")
    private Integer totalIntegral;

    @ApiModelProperty(value = "成长值")
    private Integer growth;

    @ApiModelProperty(value = "等级名称")
    private String gradeName;

    @ApiModelProperty("等级图标")
    private String gradeIcon;

    @ApiModelProperty(value = "下一等级成长值")
    private Integer nextGradeGrowth;

    @ApiModelProperty(value = "优惠券数量")
    private Long couponCount;
}
