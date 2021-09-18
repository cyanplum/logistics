package cn.sevenlion.logistics.member.manager;

import cn.sevenlion.logistics.common.mapper.member.TaskGiftMapper;
import cn.sevenlion.logistics.common.mapper.member.TaskMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: qimeiwen
 * @create: 2021-09-18
 */
@Slf4j
@Component
public class TaskManager {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskGiftMapper taskGiftMapper;
}
