package cn.sevenlion.logistics.common.model.enums;

import lombok.Getter;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/9/11 12:27 上午
 * 订单状态
 */
@Getter
public enum OrderStatusEnum {
    SUCCESS(1,"已签收"),
    DISTRIBUTION(2,"配送中"),
    REFUNDING(3,"退款中"),
    REFUNDED(4,"退款成功"),
    REFUND_FAILED(5,"退款失败");

    private Integer code;

    private String desc;

    OrderStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
