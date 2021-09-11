package cn.sevenlion.logistics.business.common.model.jsonobject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: qimeiwen
 * @create: 2021-09-11
 */
@Data
@ApiModel(value = "AddressJsonObject", description = "地址json对象")
public class AddressJsonObject {

    @ApiModelProperty("经度")
    private String longitude;

    @ApiModelProperty("纬度")
    private String latitude;

    @ApiModelProperty("省")
    private String province;

    @ApiModelProperty("市")
    private String city;

    @ApiModelProperty("区/县")
    private String district;

    @ApiModelProperty("街道")
    private String street;

    @ApiModelProperty("详细地址")
    private String detail;
}
