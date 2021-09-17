package cn.sevenlion.logistics.user.server.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: qimeiwen
 * @create: 2021-09-17
 */
@Data
@ApiModel(value = "UserDetailVo", description = "用户详细信息")
public class UserDetailVo {

    @ApiModelProperty(value = "唯一编码")
    private String serialCode;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户email")
    private String email;

    @ApiModelProperty(value = "用户状态")
    private Integer status;

    @ApiModelProperty(value = "用户电话")
    private String phone;
}
