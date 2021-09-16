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
 * 邮寄物品类型
 */
@Getter
public enum  ItemTypeEnum {

    ;

    private Integer type;

    private String name;

    ItemTypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }
}
