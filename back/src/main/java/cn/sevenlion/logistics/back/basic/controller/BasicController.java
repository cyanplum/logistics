package cn.sevenlion.logistics.back.basic.controller;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.sevenlion.logistics.back.basic.model.bo.BasicBo;
import cn.sevenlion.logistics.back.basic.model.entity.BasicEntity;
import cn.sevenlion.logistics.back.basic.model.query.BasicQueryModel;
import cn.sevenlion.logistics.back.basic.model.vo.BasicVo;
import cn.sevenlion.logistics.back.basic.serivce.BasicService;
import cn.sevenlion.logistics.back.basic.util.BasicUtils;
import cn.sevenlion.logistics.back.basic.validated.BasicValid;
import cn.sevenlion.logistics.back.basic.web.CommonResult;
import cn.sevenlion.logistics.back.basic.web.CommonResultPage;
import cn.sevenlion.logistics.back.basic.web.ConvertUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: qimeiwen
 * @create: 2021-09-30
 */
public abstract class BasicController<Entity extends BasicEntity, Bo extends BasicBo, Vo extends BasicVo, QueryModel extends BasicQueryModel, Service extends BasicService<Entity>> {

    @Autowired
    Service service;

    @ApiOperation("新增")
    @PostMapping
    public CommonResult save(@Valid @RequestBody Bo bo) {
        return CommonResult.success();
    }

    @ApiOperation("查询列表")
    @GetMapping
    public CommonResultPage<Vo> index(QueryModel queryModel) {
        Page<Entity> page = service.page(new Page<Entity>(queryModel.getPn(), queryModel.getPageSize()), queryModel.queryWrapper());
        List<Vo> result = page.getRecords().stream().map(this::entity2Vo).collect(Collectors.toList());
        return CommonResultPage.success(page, result);
    }
    @ApiOperation("根据主键查询详情")
    @GetMapping("/{serialCode}")
    public CommonResult<Vo> show(@PathVariable String serialCode) {
        return CommonResult.success(this.entity2Vo(service.getById(serialCode)));
    }

    @ApiOperation("新增")
    @PostMapping
    public CommonResult create(@Validated(BasicValid.Create.class) @RequestBody Bo bo) {
        Entity entity = bo2Entity(bo);
        entity.setSerialCode(BasicUtils.getSerialCode());
        entity.setCreateTime(LocalDateTime.now());
        service.save(entity);
        return CommonResult.success();
    }

    @ApiOperation("根据主键修改修改")
    @PutMapping("/{serialCode:.+}")
    public CommonResult update(@PathVariable String serialCode, @Validated(BasicValid.Update.class) @RequestBody Bo bo) {
        Entity entity = bo2Entity(bo);
        entity.setSerialCode(serialCode);
        entity.setUpdateTime(LocalDateTime.now());
        service.updateById(entity);
        return CommonResult.success();
    }

    @ApiOperation("根据主键删除")
    @DeleteMapping("/{serialCode:.+}")
    public CommonResult delete(@PathVariable String serialCode) {
        return CommonResult.success(service.removeById(serialCode));
    }

    public Vo entity2Vo(Entity entity) {
        return (Vo) service.entity2Vo(entity);
    }

    public Entity bo2Entity(Bo bo) {
        return service.bo2Entity(bo);
    }
}
