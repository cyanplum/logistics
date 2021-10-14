package cn.sevenlion.logistics.common.model.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: qimeiwen
 * @create: 2021-09-13
 */
@Data
public class BaseQueryModel {

    @ApiModelProperty("当前页码")
    private Integer pn = 1;


    @ApiModelProperty("页大小")
    private Integer size = 10;
}
