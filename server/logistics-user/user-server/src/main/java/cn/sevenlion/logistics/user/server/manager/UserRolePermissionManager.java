package cn.sevenlion.logistics.user.server.manager;

import cn.sevenlion.logistics.user.common.model.entity.UserRolePermissionEntity;
import cn.sevenlion.logistics.user.server.mapper.UserRolePermissionMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/9/11 12:39 上午
 */
@Component
public class UserRolePermissionManager {

    @Autowired
    private UserRolePermissionMapper userRolePermissionMapper;

    public List<UserRolePermissionEntity> selectListByRoleCode(String roleCode) {
        List<UserRolePermissionEntity> userRolePermissionEntityList = userRolePermissionMapper.selectList(new QueryWrapper<UserRolePermissionEntity>().lambda()
                .eq(UserRolePermissionEntity::getRoleCode, roleCode));
        return userRolePermissionEntityList;
    }
}
