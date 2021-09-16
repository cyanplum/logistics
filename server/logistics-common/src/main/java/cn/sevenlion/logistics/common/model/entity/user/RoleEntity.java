package cn.sevenlion.logistics.common.model.entity.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户角色表
 * </p>
 *
 * @author cyanplum
 * @since 2021-09-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_role")
@ApiModel(value="RoleEntity对象", description="用户角色表")
public class RoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableField(value = "id")
    private Integer id;

    @ApiModelProperty(value = "唯一编码")
    @TableId("serial_code")
    private String serialCode;

    @ApiModelProperty(value = "角色标签")
    @TableField("label")
    private String label;

    @ApiModelProperty(value = "角色名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "角色介绍")
    @TableField("description")
    private String description;


}
