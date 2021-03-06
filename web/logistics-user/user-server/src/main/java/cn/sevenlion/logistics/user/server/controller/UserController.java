package cn.sevenlion.logistics.user.server.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.sevenlion.logistics.common.exception.BaseException;
import cn.sevenlion.logistics.common.model.entity.user.UserEntity;
import cn.sevenlion.logistics.common.response.CommonResult;
import cn.sevenlion.logistics.common.response.CommonResultPage;
import cn.sevenlion.logistics.user.server.model.query.AddressQueryModel;
import cn.sevenlion.logistics.user.server.model.vo.AddressVo;
import cn.sevenlion.logistics.user.server.model.vo.UserDetailVo;
import cn.sevenlion.logistics.user.server.model.vo.UserVo;
import cn.sevenlion.logistics.user.server.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/9/12 5:49 下午
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation("查询用户简单信息")
    @GetMapping
    public CommonResult<UserVo> selectUser() {
        String userCode = StpUtil.getLoginIdAsString();
        return CommonResult.success(userService.selectUser(userCode));
    }

    @ApiOperation("查询用户详情")
    @GetMapping("/detail")
    public CommonResult<UserDetailVo> selectDetailUser() {
        String userCode = StpUtil.getLoginIdAsString();
        return CommonResult.success(userService.selectDetailUser(userCode));
    }



}
