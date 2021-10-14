package cn.sevenlion.logistics.user.server.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaUserService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.sevenlion.logistics.common.exception.BaseException;
import cn.sevenlion.logistics.common.mapper.user.UserMapper;
import cn.sevenlion.logistics.user.server.mamager.UserPermissionManager;
import cn.sevenlion.logistics.user.server.mamager.UserRolePermissionManager;
import cn.sevenlion.logistics.common.mapper.user.UserRoleMapper;
import cn.sevenlion.logistics.common.model.entity.user.PermissionEntity;
import cn.sevenlion.logistics.common.model.entity.user.RoleEntity;
import cn.sevenlion.logistics.common.model.entity.user.RolePermissionEntity;
import cn.sevenlion.logistics.common.model.entity.user.UserEntity;
import cn.sevenlion.logistics.common.response.CommonResult;
import cn.sevenlion.logistics.user.server.model.UserInfo;
import cn.sevenlion.logistics.user.server.model.query.UserAuthQueryModel;
import cn.sevenlion.logistics.user.server.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
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

    @Autowired
    private WxMaService wxMaService;

    @ApiOperation("登录")
    @PostMapping("/test")
    public CommonResult auth(@Valid @RequestBody UserAuthQueryModel queryModel) {
        UserEntity userEntity = userService.auth(queryModel);
        String roleCode = userEntity.getRoleCode();
        RoleEntity roleEntity = userRoleMapper.selectById(roleCode);
        if (ObjectUtil.isNull(roleEntity)) {
            throw new BaseException("用户角色不存在！");
        }
        List<RolePermissionEntity> rolePermissionEntityList = userRolePermissionManager.selectListByRoleCode(roleCode);
        if (CollUtil.isEmpty(rolePermissionEntityList)) {
            throw new BaseException("角色暂无权限！");
        }
        List<String> permissionCodeList = rolePermissionEntityList.stream().map(RolePermissionEntity::getPermissionCode).collect(Collectors.toList());
        List<PermissionEntity> permissionEntityList = userPermissionManager.selectListByCodeList(permissionCodeList);
        List<String> permissionLabelList = permissionEntityList.stream().map(it -> it.getLabel()).collect(Collectors.toList());

        //登录授权
        StpUtil.login(userEntity.getSerialCode());
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userEntity, userInfo);
        userInfo.setRoleName(roleEntity.getLabel());
        userInfo.setPermissionList(permissionLabelList);
        StpUtil.getSession().set("userInfo",userInfo);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        Map<String, String> result  = new HashMap<>();
        result.put("token", tokenInfo.getTokenValue());
        return CommonResult.success(result);
    }

    @ApiModelProperty
    @PostMapping
    public CommonResult login(@RequestBody UserAuthQueryModel queryModel) {
        String code = queryModel.getCode();
        try {
            WxMaUserService wxMaUserService = wxMaService.getUserService();
            WxMaJscode2SessionResult session = wxMaUserService.getSessionInfo(code);
            String openid = session.getOpenid();
            System.out.println(code);
            System.out.println(openid);
        } catch (Exception e) {

        }
        return CommonResult.success();
    }

    @ApiModelProperty
    @GetMapping
    public CommonResult logins(UserAuthQueryModel queryModel) {
        String code = queryModel.getCode();
        try {
            WxMaUserService wxMaUserService = wxMaService.getUserService();
            WxMaJscode2SessionResult session = wxMaUserService.getSessionInfo(code);
            String openid = session.getOpenid();
            System.out.println(code);
            System.out.println(openid);
        } catch (Exception e) {

        }
        return CommonResult.success();
    }

    @GetMapping("test")
    public CommonResult test() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CommonResult.success();
    }
}
