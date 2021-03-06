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
@ApiModel(value="RolePermissionEntity对象", description="用户角色权限表")
public class RolePermissionEntity implements Serializable {

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
