package cn.sevenlion.logistics.member.service;

import cn.sevenlion.logistics.member.model.query.TaskQueryModel;
import cn.sevenlion.logistics.member.model.vo.TaskVo;

import java.util.List;

/**
 * @author: qimeiwen
 * @create: 2021-09-18
 */
public interface TaskService {
    /**
     * 查询任务列表
     * @param taskQueryModel
     * @return
     */
    List<TaskVo> selectList(TaskQueryModel taskQueryModel);
}
