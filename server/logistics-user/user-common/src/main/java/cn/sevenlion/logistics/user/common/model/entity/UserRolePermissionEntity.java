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
 * 用户角色权限表
 * </p>
 *
 * @author cyanplum
 * @since 2021-09-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_role_permission")
@ApiModel(value="UserRolePermissionEntity对象", description="用户角色权限表")
public class UserRolePermissionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(value = "id")
    private Integer id;

    @TableId("serial_code")
    private String serialCode;

    @ApiModelProperty(value = "角色id")
    @TableField("role_code")
    private String roleCode;

    @ApiModelProperty(value = "权限id")
    @TableField("permission_code")
    private String permissionCode;


}
