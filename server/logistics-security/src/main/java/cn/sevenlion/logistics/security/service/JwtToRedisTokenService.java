package cn.sevenlion.logistics.security.service;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import cn.sevenlion.logistics.security.model.token.SevenlionAuthenticationUser;
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
 * @date 2021/5/13 12:40 下午
 */
public class JwtToRedisTokenService {

    private RedisTemplate<String,Object> redisTemplate;

    public JwtToRedisTokenService(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    private SevenlionSecurityProperties properties;

    public static final String SECURITY_KEY = "sevenlion:";

    public String createToken(String token, SevenlionAuthenticationUser userDetails) {
        Object key = redisTemplate.opsForValue().get(SECURITY_KEY + properties.getName() + userDetails.getId());
        if (key != null) {
            return (String) key;
        }
        TimeBasedGenerator timeBasedGenerator = Generators.timeBasedGenerator(EthernetAddress.fromInterface());
        String redisToken = timeBasedGenerator.generate().toString();
        redisTemplate.opsForValue().set( SECURITY_KEY + properties.getName() + userDetails.getId(),redisToken,properties.getAccessTokenExpireIn(),TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(properties.getName() + redisToken, token,properties.getAccessTokenExpireIn(),TimeUnit.SECONDS);
        return redisToken;
    }

    public String readToken(String token) {
        Object key = redisTemplate.opsForValue().get(properties.getName() + token);
        return (String) key;
    }

}
