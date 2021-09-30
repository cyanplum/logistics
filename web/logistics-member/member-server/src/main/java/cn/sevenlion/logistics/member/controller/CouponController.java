package cn.sevenlion.logistics.member.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.sevenlion.logistics.common.response.CommonResult;
import cn.sevenlion.logistics.common.response.CommonResultPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import cn.sevenlion.logistics.member.model.bo.CouponBindBo;
import cn.sevenlion.logistics.member.model.query.CouponQueryModel;
import cn.sevenlion.logistics.member.model.vo.CouponVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.sevenlion.logistics.member.service.CouponService;

import javax.validation.Valid;

/**
 * @author: qimeiwen
 * @create: 2021-09-17
 */
@Api(tags = "卡券操作")
@RestController
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @ApiOperation("查询卡券列表")
    @GetMapping
    public CommonResultPage<CouponVo> selectCouponPage(CouponQueryModel queryModel) {
        return CommonResultPage.success(couponService.selectCouponPage(queryModel));
    }

    @ApiOperation("查询卡券详情")
    @GetMapping("/{serialCode:.+}")
    @ApiImplicitParam(value = "serialCode", name = "卡券批次编号", paramType = "path",  dataTypeClass = String.class)
    public CommonResult<CouponVo> selectCouponByBatchId(@PathVariable String serialCode) {
        return CommonResult.success(couponService.selectCouponByBatchId(serialCode));
    }

    @ApiOperation("查询用户绑定卡券列表")
    @GetMapping("/user")
    public CommonResultPage<CouponVo> selectBindCouponPageByUser(CouponQueryModel queryModel) {
        String userCode = StpUtil.getLoginIdAsString();
        return CommonResultPage.success(couponService.selectBindCouponPageByUser(userCode, queryModel));
    }

    @ApiOperation("查询用户绑定卡券详情")
    @GetMapping("/user/{serialCode:.+}")
    @ApiImplicitParam(value = "serialCode", name = "卡券编码", paramType = "path", dataTypeClass = String.class, required = true)
    public CommonResult<CouponVo> selectBindCouponByByUser(@PathVariable String serialCode) {
        String userCode = StpUtil.getLoginIdAsString();
        return CommonResult.success(couponService.selectBindCouponByByUser(userCode, serialCode));
    }

    @ApiOperation("领取卡券")
    @PostMapping
    public CommonResult receiveCoupon(@Valid @RequestBody CouponBindBo bo) {
        String userCode = StpUtil.getLoginIdAsString();
        couponService.receiveCoupon(userCode, bo);
        return CommonResult.success();
    }
}
