package cn.sevenlion.logistics.user.server.model;

import lombok.Data;

import java.util.List;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/9/11 12:50 上午
 */
@Data
public class UserInfo {

    private String serialCode;

    private String username;

    private String roleCode;

    private String roleName;

    private List<String> permissionList;
}
