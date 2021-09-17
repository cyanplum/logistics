package cn.sevenlion.logistics.user.server.mamager;

import cn.sevenlion.logistics.common.mapper.user.UserPermissionMapper;
import cn.sevenlion.logistics.common.model.entity.user.PermissionEntity;
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

    public List<PermissionEntity> selectListByCodeList(List<String> permissionCodeList) {
        List<PermissionEntity> permissionEntityList = userPermissionMapper.selectList(new QueryWrapper<PermissionEntity>().lambda()
                .in(PermissionEntity::getSerialCode, permissionCodeList));
        return permissionEntityList;
    }
}
