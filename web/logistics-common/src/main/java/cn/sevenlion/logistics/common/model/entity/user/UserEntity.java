package cn.sevenlion.logistics.common.model.entity.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author cyanplum
 * @since 2021-09-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
@ApiModel(value="UserEntity对象", description="用户表")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(value = "id")
    private Long id;

    @ApiModelProperty(value = "唯一编码")
    @TableId("serial_code")
    private String serialCode;

    @ApiModelProperty(value = "用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "用户密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "用户email")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "用户角色")
    @TableField("role_code")
    private String roleCode;

    @ApiModelProperty(value = "用户状态")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "用户电话")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "当前积分")
    @TableField("integral")
    private Integer integral;

    @ApiModelProperty(value = "历史总计获得积分")
    @TableField("total_integral")
    private Integer totalIntegral;

    @ApiModelProperty(value = "成长值")
    @TableField("growth")
    private Integer growth;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "逻辑删除")
    @TableField("deleted")
    @TableLogic
    private Integer deleted;


}
