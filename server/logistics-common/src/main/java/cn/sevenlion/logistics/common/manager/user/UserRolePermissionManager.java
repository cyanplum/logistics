package cn.sevenlion.logistics.common.manager.user;

import cn.sevenlion.logistics.common.mapper.user.UserRolePermissionMapper;
import cn.sevenlion.logistics.common.model.entity.user.RolePermissionEntity;
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

    public List<RolePermissionEntity> selectListByRoleCode(String roleCode) {
        List<RolePermissionEntity> rolePermissionEntityList = userRolePermissionMapper.selectList(new QueryWrapper<RolePermissionEntity>().lambda()
                .eq(RolePermissionEntity::getRoleCode, roleCode));
        return rolePermissionEntityList;
    }
}
