package cn.sevenlion.logistics.member.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: qimeiwen
 * @create: 2021-09-17
 */
@Data
@ApiModel(value = "CouponBindBo", description = "卡券绑定参数")
public class CouponBindBo {

    @ApiModelProperty(value = "卡券code")
    private String couponCode;

    @ApiModelProperty(value = "卡券批次code")
    private String batchCode;
}
