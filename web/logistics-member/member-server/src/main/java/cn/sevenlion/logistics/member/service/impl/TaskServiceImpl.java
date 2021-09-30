package cn.sevenlion.logistics.member.service.impl;

import cn.sevenlion.logistics.common.mapper.member.TaskMapper;
import cn.sevenlion.logistics.common.model.entity.member.TaskEntity;
import cn.sevenlion.logistics.common.model.entity.member.TaskGiftEntity;
import cn.sevenlion.logistics.member.manager.TaskManager;
import cn.sevenlion.logistics.member.model.query.TaskQueryModel;
import cn.sevenlion.logistics.member.model.vo.TaskGiftVo;
import cn.sevenlion.logistics.member.model.vo.TaskVo;
import cn.sevenlion.logistics.member.service.TaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        //获取任务列表
        List<TaskEntity> taskEntityList = taskManager.selectList();
        if (taskEntityList.isEmpty()) {
            return Lists.newArrayList();
        }
        List<TaskVo> taskVoList = taskEntityList.stream().map(it -> {
            TaskVo vo = new TaskVo();
            BeanUtils.copyProperties(it, vo);
            //根据任务得到任务礼物列表
            List<TaskGiftEntity> taskGiftEntityList = taskManager.selectTaskGiftList(it.getSerialCode());
            if (taskGiftEntityList.isEmpty()) {
                return vo;
            }
            //处理任务礼物列表
            List<TaskGiftVo> giftVoList = taskGiftEntityList.stream().map(p -> {
                TaskGiftVo giftVo = new TaskGiftVo();
                return giftVo;
            }).sorted((o1, o2) -> o2.getGiftCycle() - o1.getGiftCycle()).collect(Collectors.toList());
            vo.setTaskGiftVoList(giftVoList);
            return vo;
        }).collect(Collectors.toList());
        return taskVoList;
    }
}
