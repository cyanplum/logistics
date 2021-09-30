package cn.sevenlion.logistics.member.manager;

import cn.sevenlion.logistics.common.consts.RedisConst;
import cn.sevenlion.logistics.common.mapper.member.TaskGiftMapper;
import cn.sevenlion.logistics.common.mapper.member.TaskMapper;
import cn.sevenlion.logistics.common.model.entity.member.TaskEntity;
import cn.sevenlion.logistics.common.model.entity.member.TaskGiftEntity;
import cn.sevenlion.logistics.common.util.ObjectMapperUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private TaskGiftMapper taskGiftMapper;

    public List<TaskEntity> selectList() {
        List<Object> values = redisTemplate.opsForHash().values(RedisConst.TASK_MAP);
        List<TaskEntity> taskEntityList = ObjectMapperUtil.getObjectMapper().convertValue(values, new TypeReference<List<TaskEntity>>() {});
        return taskEntityList;
    }

    public List<TaskGiftEntity> selectTaskGiftList(String taskCode) {
        String key = String.format(RedisConst.TASK_GIFT_MAP, taskCode);
        List<Object> values = redisTemplate.opsForHash().values(key);
        List<TaskGiftEntity> taskGiftEntityList = ObjectMapperUtil.getObjectMapper().convertValue(values, new TypeReference<List<TaskGiftEntity>>() {});
        return taskGiftEntityList;
    }
}
