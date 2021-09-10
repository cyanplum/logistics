package cn.sevenlion.logistics.user.server.service.impl;

import cn.sevenlion.logistics.user.server.manager.UserManager;
import cn.sevenlion.logistics.user.server.mapper.UserMapper;
import cn.sevenlion.logistics.user.server.model.bo.UserAuthBo;
import cn.sevenlion.logistics.user.server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/9/10 10:27 下午
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserManager userManager;

    @Autowired
    UserMapper userMapper;


    @Override
    public boolean auth(UserAuthBo bo) {
        return false;
    }
}
