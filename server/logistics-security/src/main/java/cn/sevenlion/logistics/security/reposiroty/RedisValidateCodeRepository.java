package cn.sevenlion.logistics.security.reposiroty;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import cn.sevenlion.logistics.security.enums.ValidateCodeType;
import cn.sevenlion.logistics.security.model.ValidateCode;
import cn.sevenlion.logistics.security.model.image.ImageValidateCode;
import cn.sevenlion.logistics.security.properties.SevenlionSecurityProperties;

import java.util.concurrent.TimeUnit;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/21 12:19 下午
 */
public class RedisValidateCodeRepository implements ValidateCodeRepository {

    private RedisTemplate<String,ValidateCode> redisTemplate;

    @Autowired
    private SevenlionSecurityProperties sevenlionSecurityProperties;

    public RedisValidateCodeRepository(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void save(ValidateCodeType type, String key, ValidateCode code) {
        String redisKey = buildKey(type, key);
        if (code instanceof ImageValidateCode) {
            ImageValidateCode imageCode = new ImageValidateCode();
            BeanUtils.copyProperties(code,imageCode);
            imageCode.setImage(null);
            redisTemplate.opsForValue().set(redisKey, imageCode, imageCode.getExpireIn(), TimeUnit.MINUTES);
        }else {
            redisTemplate.opsForValue().set(redisKey, code, code.getExpireIn(), TimeUnit.MINUTES);
        }
    }

    @Override
    public ValidateCode get(ValidateCodeType type, String key) {
        String redisKey = buildKey(type, key);
        return (ValidateCode) redisTemplate.opsForValue().get(redisKey);
    }

    @Override
    public void remove(ValidateCodeType type, String key) {
        String redisKey = buildKey(type, key);
        redisTemplate.delete(redisKey);
    }
    private String buildKey(ValidateCodeType type, String key) {
        return type.getPrefix() + "_" + sevenlionSecurityProperties.getName()+"-"+key;
    }
}
