package cn.sevenlion.logistics.user.server.service;

import cn.sevenlion.logistics.user.common.model.entity.UserEntity;
import cn.sevenlion.logistics.user.server.model.bo.UserAuthBo;
import cn.sevenlion.logistics.user.server.model.query.UserAuthQueryModel;

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
     * @param queryModel
     * @return
     */
    UserEntity auth(UserAuthQueryModel queryModel);
}
