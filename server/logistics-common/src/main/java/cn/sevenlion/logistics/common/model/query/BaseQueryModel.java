package cn.sevenlion.logistics.common.model.query;

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
 * @date 2021/9/12 8:39 下午
 */
@Data
public class BaseQueryModel {


    @ApiModelProperty("当前页码")
    private Integer pn = 1;


    @ApiModelProperty("页大小")
    private Integer size = 10;
}
