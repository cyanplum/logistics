package cn.sevenlion.logistics.common.model.enums;

import lombok.Getter;

/**
 * @author: qimeiwen
 * @create: 2021-09-17
 */
@Getter
public enum CouponExchangeTypeEnum {

    INTEGRAL(1,"积分兑换");

    private Integer code;

    private String desc;

    CouponExchangeTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
