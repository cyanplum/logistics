package cn.sevenlion.logistics.user.common.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户权限表
 * </p>
 *
 * @author cyanplum
 * @since 2021-09-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_permission")
@ApiModel(value="UserPermissionEntity对象", description="用户权限表")
public class UserPermissionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(value = "id")
    private Integer id;

    @ApiModelProperty(value = "唯一编码")
    @TableId("serial_code")
    private String serialCode;

    @ApiModelProperty(value = "父权限标签")
    @TableField("p_code")
    private Integer pCode;

    @ApiModelProperty(value = "权限标签")
    @TableField("label")
    private String label;

    @ApiModelProperty(value = "前端菜单索引")
    @TableField("reference")
    private String reference;

    @ApiModelProperty(value = "权限名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "权限介绍")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "菜单图标")
    @TableField("icon")
    private String icon;


}
