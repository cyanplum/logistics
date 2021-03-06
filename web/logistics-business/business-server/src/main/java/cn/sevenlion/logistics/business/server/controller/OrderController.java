package cn.sevenlion.logistics.business.server.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.sevenlion.logistics.business.server.model.bo.OrderBo;
import cn.sevenlion.logistics.business.server.model.query.OrderQueryModel;
import cn.sevenlion.logistics.business.server.model.vo.OrderVo;
import cn.sevenlion.logistics.business.server.service.OrderService;
import cn.sevenlion.logistics.common.exception.BaseException;
import cn.sevenlion.logistics.common.model.enums.MailServiceTypeEnum;
import cn.sevenlion.logistics.common.response.CommonResult;
import cn.sevenlion.logistics.common.response.CommonResultPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/9/11 12:27 上午
 */
@Api(tags = "订单管理")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @ApiOperation("下单")
    @PostMapping
    public CommonResult order(@Valid @RequestBody OrderBo orderBo) {
        //检查
        check(orderBo);
        orderService.submit(orderBo);
        return CommonResult.success();
    }

    @ApiOperation("查询订单列表")
    @GetMapping
    public CommonResultPage<OrderVo> selectOrderPage(OrderQueryModel queryModel) {
        String userCode = StpUtil.getLoginIdAsString();
        return CommonResultPage.success(orderService.selectOrderPage(userCode, queryModel));
    }

    @ApiOperation("查询订单详情")
    @GetMapping("/{serialCode:.+}")
    public CommonResult<OrderVo> selectById(@PathVariable String serialCode) {
        String userCode = StpUtil.getLoginIdAsString();
        return CommonResult.success(orderService.selectById(userCode, serialCode));
    }

    @ApiOperation("计算价格")
    @PostMapping("/calculate")
    public CommonResult<BigDecimal> calculate(@RequestBody OrderBo orderBo) {
        BigDecimal price = orderService.calculate(orderBo);
        return CommonResult.success(price);
    }

    private void check(OrderBo orderBo) {
        //邮寄服务类型是上门自取，需要天蝎上门服务时间
        if (orderBo.getMailServiceType().equals(MailServiceTypeEnum.SINCE.getCode())) {
            if (ObjectUtil.isNull(orderBo.getMailServiceTime())) {
                throw new BaseException("服务时间不能为空！");
            }
            LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(30);
            if (orderBo.getMailServiceTime().isBefore(localDateTime)) {
                throw new BaseException("服务时间至少在当前半小时之后！");
            }
        }
    }
}
