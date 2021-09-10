package cn.sevenlion.logistics.user.server.manager;

import cn.sevenlion.logistics.user.common.model.entity.UserPermissionEntity;
import cn.sevenlion.logistics.user.server.mapper.UserPermissionMapper;
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
public class UserPermissionManager {

    @Autowired
    private UserPermissionMapper userPermissionMapper;

    public List<UserPermissionEntity> selectListByCodeList(List<String> permissionCodeList) {
        List<UserPermissionEntity> userPermissionEntityList = userPermissionMapper.selectList(new QueryWrapper<UserPermissionEntity>().lambda()
                .in(UserPermissionEntity::getSerialCode, permissionCodeList));
        return userPermissionEntityList;
    }
}
