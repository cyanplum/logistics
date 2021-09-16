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
 * @date 2021/9/11 12:25 上午
 * 邮寄服务类型
 */
@Getter
public enum MailServiceTypeEnum {

    VISITING(1,"上门服务"),
    SINCE(2,"邮寄点自寄");

    private Integer type;

    private String name;

    MailServiceTypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

}
