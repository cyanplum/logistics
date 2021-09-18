package cn.sevenlion.logistics.member.service.impl;

import cn.sevenlion.logistics.common.mapper.member.TaskMapper;
import cn.sevenlion.logistics.common.model.entity.member.TaskEntity;
import cn.sevenlion.logistics.member.manager.TaskManager;
import cn.sevenlion.logistics.member.model.query.TaskQueryModel;
import cn.sevenlion.logistics.member.model.vo.TaskVo;
import cn.sevenlion.logistics.member.service.TaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: qimeiwen
 * @create: 2021-09-18
 */
@Slf4j
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, TaskEntity> implements TaskService {

    @Autowired
    private TaskManager taskManager;

    @Override
    public List<TaskVo> selectList(TaskQueryModel taskQueryModel) {
        return null;
    }
}
