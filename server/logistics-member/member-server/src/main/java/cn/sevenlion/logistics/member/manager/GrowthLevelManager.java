package cn.sevenlion.logistics.member.manager;

import cn.sevenlion.logistics.common.consts.RedisConst;
import cn.sevenlion.logistics.common.mapper.member.GrowthLevelMapper;
import cn.sevenlion.logistics.common.model.entity.member.GrowthLevelEntity;
import cn.sevenlion.logistics.common.model.entity.user.UserEntity;
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
 * @create: 2021-09-17
 */
@Slf4j
@Component
public class GrowthLevelManager {

    @Autowired
    private GrowthLevelMapper growthLevelMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public GrowthLevelEntity selectUserGrowthLevel(UserEntity userEntity) {
        List<GrowthLevelEntity> growthLevelEntityList = null;
        try {
            //缓存中取等级列表
            List<Object> values = redisTemplate.opsForHash().values(RedisConst.GROWTH_LEVEL_MAP);
            growthLevelEntityList = ObjectMapperUtil.getObjectMapper().convertValue(values, new TypeReference<List<GrowthLevelEntity>>() {});
        } catch (Exception e) {
            log.warn("redis获取等级列表失败！原因:{}", e.getMessage());
            //查数据库
            growthLevelEntityList = selectList();
            try {
                //放入缓存
                Map<String, GrowthLevelEntity> map = growthLevelEntityList.stream().collect(Collectors.toMap(GrowthLevelEntity::getSerialCode, Function.identity()));
                redisTemplate.opsForHash().putAll(RedisConst.GROWTH_LEVEL_MAP, map);
            }catch (Exception ex) {
                log.warn("redis存储等级列表失败原因:{}", ex.getMessage());
            }
        }
        //设置等级成长值
        for (GrowthLevelEntity growthLevelEntity : growthLevelEntityList) {
            if (growthLevelEntity.getStartValue() <= userEntity.getGrowth() && userEntity.getGrowth() < growthLevelEntity.getEndValue()) {
                return growthLevelEntity;
            }
        }
        return null;
    }
    public List<GrowthLevelEntity> selectList() {
        return growthLevelMapper.selectList(new QueryWrapper<GrowthLevelEntity>()
                .lambda().orderByAsc(GrowthLevelEntity::getGrade));
    }
}
