package cn.sevenlion.logistics.user.server.service;

import cn.sevenlion.logistics.user.server.model.bo.UserAuthBo;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/9/10 10:28 下午
 */
public interface UserService {
    /**
     * 登录校验
     * @param bo
     * @return
     */
    boolean auth(UserAuthBo bo);
}
