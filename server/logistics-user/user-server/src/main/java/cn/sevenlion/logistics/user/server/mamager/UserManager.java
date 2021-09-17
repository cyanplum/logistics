package cn.sevenlion.logistics.user.server.mamager;

import cn.sevenlion.logistics.common.consts.RedisConst;
import cn.sevenlion.logistics.common.mapper.user.UserMapper;
import cn.sevenlion.logistics.common.model.entity.member.GrowthLevelEntity;
import cn.sevenlion.logistics.common.model.entity.user.UserEntity;
import cn.sevenlion.logistics.common.util.ObjectMapperUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/9/10 10:36 下午
 */
@Slf4j
@Component
public class UserManager {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public UserEntity selectById(String userCode) {
        UserEntity userEntity = userMapper.selectById(userCode);
        return userEntity;
    }

    public GrowthLevelEntity selectUserGrowthLevel(UserEntity userEntity) {
        List<GrowthLevelEntity> growthLevelEntityList = null;
        try {
            List<Object> values = redisTemplate.opsForHash().values(RedisConst.GROWTH_LEVEL_MAP);
            growthLevelEntityList = ObjectMapperUtil.getObjectMapper().convertValue(values, new TypeReference<List<GrowthLevelEntity>>() {});
        } catch (Exception e) {
            log.warn("redis获取等级列表失败！");
            // TODO: 2021/9/17 远程调用获取等级成长值列表
        }
        //设置等级成长值
        for (GrowthLevelEntity growthLevelEntity : growthLevelEntityList) {
            if (growthLevelEntity.getStartValue() <= userEntity.getGrowth() && userEntity.getGrowth() < growthLevelEntity.getEndValue()) {
                return growthLevelEntity;
            }
        }
        return null;
    }
}
