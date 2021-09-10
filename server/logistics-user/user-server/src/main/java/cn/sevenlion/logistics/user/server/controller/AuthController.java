package cn.sevenlion.logistics.user.server.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.sevenlion.logistics.common.exception.BaseException;
import cn.sevenlion.logistics.common.response.CommonResult;
import cn.sevenlion.logistics.user.common.model.entity.UserEntity;
import cn.sevenlion.logistics.user.common.model.entity.UserPermissionEntity;
import cn.sevenlion.logistics.user.common.model.entity.UserRoleEntity;
import cn.sevenlion.logistics.user.common.model.entity.UserRolePermissionEntity;
import cn.sevenlion.logistics.user.server.manager.UserPermissionManager;
import cn.sevenlion.logistics.user.server.manager.UserRolePermissionManager;
import cn.sevenlion.logistics.user.server.mapper.UserRoleMapper;
import cn.sevenlion.logistics.user.server.model.UserInfo;
import cn.sevenlion.logistics.user.server.model.bo.UserAuthBo;
import cn.sevenlion.logistics.user.server.model.query.UserAuthQueryModel;
import cn.sevenlion.logistics.user.server.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/9/10 10:09 下午
 */
@Api(tags = "权限管理")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserRolePermissionManager userRolePermissionManager;

    @Autowired
    private UserPermissionManager userPermissionManager;

    @ApiOperation("登录")
    @PostMapping
    public CommonResult auth(@Valid @RequestBody UserAuthQueryModel queryModel) {
        UserEntity userEntity = userService.auth(queryModel);
        String roleCode = userEntity.getRoleCode();
        UserRoleEntity userRoleEntity = userRoleMapper.selectById(roleCode);
        if (ObjectUtil.isNull(userRoleEntity)) {
            throw new BaseException("用户角色不存在！");
        }
        List<UserRolePermissionEntity> userRolePermissionEntityList = userRolePermissionManager.selectListByRoleCode(roleCode);
        if (CollUtil.isEmpty(userRolePermissionEntityList)) {
            throw new BaseException("角色暂无权限！");
        }
        List<String> permissionCodeList = userRolePermissionEntityList.stream().map(UserRolePermissionEntity::getPermissionCode).collect(Collectors.toList());
        List<UserPermissionEntity> userPermissionEntityList = userPermissionManager.selectListByCodeList(permissionCodeList);
        List<String> permissionLabelList = userPermissionEntityList.stream().map(it -> it.getLabel()).collect(Collectors.toList());

        //登录授权
        StpUtil.login(userEntity.getSerialCode());
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userEntity, userInfo);
        userInfo.setRoleName(userRoleEntity.getLabel());
        userInfo.setPermissionList(permissionLabelList);
        StpUtil.getSession().set("userInfo",userInfo);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        Map<String, String> result  = new HashMap<>();
        result.put("token", tokenInfo.getTokenValue());
        return CommonResult.success(result);
    }

    @GetMapping
    public CommonResult test() {
        System.out.println(StpUtil.isLogin());
        return CommonResult.success();
    }
}
