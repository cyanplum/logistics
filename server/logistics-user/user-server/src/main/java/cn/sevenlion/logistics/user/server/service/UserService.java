package cn.sevenlion.logistics.user.server.service;

import cn.sevenlion.logistics.common.model.entity.user.UserEntity;
import cn.sevenlion.logistics.user.server.model.query.UserAuthQueryModel;
import cn.sevenlion.logistics.user.server.model.vo.UserDetailVo;
import cn.sevenlion.logistics.user.server.model.vo.UserVo;

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

    /**
     * 查询用户信息
     * @param userCode
     * @return
     */
    UserVo selectUser(String userCode);

    /**
     * 查询用户详细信息
     * @param userCode
     * @return
     */
    UserDetailVo selectDetailUser(String userCode);
}
