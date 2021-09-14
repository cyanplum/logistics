package cn.sevenlion.logistics.user.server.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.sevenlion.logistics.common.response.CommonResult;
import cn.sevenlion.logistics.common.response.CommonResultPage;
import cn.sevenlion.logistics.user.server.model.bo.AddressBo;
import cn.sevenlion.logistics.user.server.model.query.AddressQueryModel;
import cn.sevenlion.logistics.user.server.model.vo.AddressVo;
import cn.sevenlion.logistics.user.server.service.AddressService;
import cn.sevenlion.logistics.user.server.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author: qimeiwen
 * @create: 2021-09-14
 */
@Api(tags = "地址管理")
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @ApiOperation("地址列表")
    @GetMapping
    public CommonResultPage<AddressVo> findPage(AddressQueryModel queryModel) {
        Page<AddressVo> addressVoPage = addressService.findPage(StpUtil.getLoginIdAsString() ,queryModel);
        return CommonResultPage.success(addressVoPage);
    }

    @ApiOperation("查询地址详情")
    @GetMapping("/{serialCode:.+}")
    public CommonResult<AddressVo> query(@PathVariable String serialCode) {
        return CommonResult.success(addressService.selectByCode(StpUtil.getLoginIdAsString(), serialCode));
    }

    @ApiOperation("修改地址")
    @PutMapping("/{serialCode:.+}")
    public CommonResult update(@PathVariable String serialCode, @Valid @RequestBody AddressBo bo) {
        return CommonResult.success(addressService.update(serialCode, bo));
    }

    @ApiOperation("新增地址")
    @PostMapping
    public CommonResult insert(@Valid @RequestBody AddressBo bo) {
        return CommonResult.success(addressService.insert(bo));
    }

    @ApiOperation("删除地址")
    @DeleteMapping("/serialCode:.+")
    public CommonResult delete(@PathVariable String serialCode) {
        return CommonResult.success(addressService.delete(serialCode));
    }
}
