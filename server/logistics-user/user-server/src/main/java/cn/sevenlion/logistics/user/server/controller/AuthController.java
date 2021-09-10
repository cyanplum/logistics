package cn.sevenlion.logistics.user.server.controller;

import cn.sevenlion.logistics.common.exception.BaseException;
import cn.sevenlion.logistics.common.response.CommonResult;
import cn.sevenlion.logistics.user.server.model.bo.UserAuthBo;
import cn.sevenlion.logistics.user.server.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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

    @ApiOperation("登录")
    @PostMapping
    public CommonResult auth(@Valid @RequestBody UserAuthBo bo) {
        boolean auth = userService.auth(bo);
        if (auth) {
            throw new BaseException("用户名或密码错误！");
        }
        return CommonResult.success();
    }
}
