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
 * @date 2021/9/11 12:26 上午
 * 支付方式类型 货到付款、现结
 */
@Getter
public enum PayTypeEnum {

    NOW(1,"现结"),
    DELIVERY(2,"货到付款");

    private Integer type;

    private String name;

    PayTypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }
}
