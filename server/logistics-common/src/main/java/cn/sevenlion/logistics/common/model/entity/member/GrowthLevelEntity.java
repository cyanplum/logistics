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
 * 
 * </p>
 *
 * @author cyanplum
 * @since 2021-09-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("member_growth_level")
@ApiModel(value="MemberGrowthLevelEntity对象", description="")
public class GrowthLevelEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "唯一编码")
    @TableField("serial_code")
    private String serialCode;

    @ApiModelProperty(value = "名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "开始值")
    @TableField("start_value")
    private Integer startValue;

    @ApiModelProperty(value = "结束值")
    @TableField("end_value")
    private Integer endValue;

    @ApiModelProperty(value = "徽章图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "等级")
    @TableField("grade")
    private Integer grade;

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
