package cn.sevenlion.logistics.member.model.query;

import cn.sevenlion.logistics.common.model.query.BaseQueryModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: qimeiwen
 * @create: 2021-09-17
 */
@Data
@ApiModel(value = "CouponQueryModel", description = "卡券查询参数")
public class CouponQueryModel extends BaseQueryModel {

    /**
     * 卡券状态
     */
    @ApiModelProperty("卡券状态")
    private Integer status;

    @ApiModelProperty("卡券名称")
    private String name;

    @ApiModelProperty("卡券类型")
    private Integer type;
}
