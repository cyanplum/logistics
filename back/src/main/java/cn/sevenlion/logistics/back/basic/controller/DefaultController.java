package cn.sevenlion.logistics.back.basic.controller;

import cn.sevenlion.logistics.back.basic.model.bo.BasicBo;
import cn.sevenlion.logistics.back.basic.model.query.BasicQueryModel;
import cn.sevenlion.logistics.back.basic.model.vo.BasicVo;
import cn.sevenlion.logistics.back.basic.serivce.BasicService;
import cn.sevenlion.logistics.back.basic.web.CommonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @author: qimeiwen
 * @create: 2021-09-30
 */
public abstract class DefaultController<Bo extends BasicBo, Vo extends BasicVo, QueryModel extends BasicQueryModel, Service extends BasicService> {

    @Autowired
    Service service;

    @ApiOperation("新增")
    @PostMapping
    public CommonResult save(@Valid @RequestBody Bo bo) {
        return CommonResult.success();
    }
}
