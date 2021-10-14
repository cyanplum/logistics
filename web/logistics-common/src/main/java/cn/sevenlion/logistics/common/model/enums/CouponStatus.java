package cn.sevenlion.logistics.common.model.enums;

import lombok.Getter;

/**
 * @author: qimeiwen
 * @create: 2021-09-17
 */
@Getter
public enum CouponStatus {
    WAITING(1,"待使用");

    private Integer code;

    private String desc;

    CouponStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
