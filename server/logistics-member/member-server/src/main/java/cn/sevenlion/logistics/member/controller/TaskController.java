package cn.sevenlion.logistics.member.controller;

import cn.sevenlion.logistics.common.response.CommonResult;
import cn.sevenlion.logistics.common.response.CommonResultPage;
import cn.sevenlion.logistics.member.model.query.TaskQueryModel;
import cn.sevenlion.logistics.member.model.vo.TaskVo;
import cn.sevenlion.logistics.member.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: qimeiwen
 * @create: 2021-09-18
 */
@RestController
@RequestMapping("/task")
@Api(tags = "任务管理")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @ApiModelProperty("查询任务列表")
    @GetMapping
    public CommonResult<List<TaskVo>> selectList(TaskQueryModel taskQueryModel) {
        return CommonResult.success(taskService.selectList(taskQueryModel));
    }

}
