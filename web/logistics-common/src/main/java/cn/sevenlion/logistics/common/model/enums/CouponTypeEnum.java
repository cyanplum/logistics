package cn.sevenlion.logistics.common.model.enums;

import lombok.Getter;

/**
 * @author: qimeiwen
 * @create: 2021-09-17
 */
@Getter
public enum CouponTypeEnum {
    DISCOUNT(1,"折扣券"),
    DEDUCTION(2, "抵扣券");
    private Integer code;

    private String desc;

    CouponTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
