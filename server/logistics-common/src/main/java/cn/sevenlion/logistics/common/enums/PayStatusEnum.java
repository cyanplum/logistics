package cn.sevenlion.logistics.common.enums;

import lombok.Getter;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/9/11 12:25 上午
 * 订单支付状态
 */
@Getter
public enum  PayStatusEnum {
    SUCCESS(1,"支付成功"),
    WAITING(2,"待支付"),;

    private Integer status;

    private String msg;

    PayStatusEnum(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
