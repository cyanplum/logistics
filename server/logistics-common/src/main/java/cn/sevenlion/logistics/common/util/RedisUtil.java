package cn.sevenlion.logistics.common.util;

import cn.hutool.core.util.ObjectUtil;
import cn.sevenlion.logistics.common.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;


/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/9/12 5:24 下午
 */
public class RedisUtil {

    public static Logger log = LoggerFactory.getLogger(RedisUtil.class);

    private static RedisTemplate getRedisTemplate() {
        RedisTemplate redisTemplate = SpringApplicationContext.getBean(RedisTemplate.class);
        if (ObjectUtil.isNull(redisTemplate)) {
            log.warn("redisTemplate获取失败！");
            throw new BaseException("内部异常！");
        }
        return redisTemplate;
    }

    public static Long getIncr(String key) {
        return getRedisTemplate().opsForValue().increment(key);
    }
}
