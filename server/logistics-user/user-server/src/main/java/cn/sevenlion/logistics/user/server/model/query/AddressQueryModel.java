package cn.sevenlion.logistics.user.server.model.query;

import cn.sevenlion.logistics.common.model.query.BaseQueryModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/9/12 8:35 下午
 */
@Data
@ApiModel("地址查询对象")
public class AddressQueryModel extends BaseQueryModel {

    @ApiModelProperty("名称")
    private String username;

    @ApiModelProperty("电话")
    private String phone;
}
