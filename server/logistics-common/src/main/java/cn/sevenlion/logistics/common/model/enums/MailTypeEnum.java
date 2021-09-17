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
 * @date 2021/9/11 12:24 上午
 * 邮寄类型
 */
@Getter
public enum  MailTypeEnum {
    ;

    private Integer code;

    private String desc;

    MailTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
