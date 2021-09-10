package cn.sevenlion.logistics.security.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/21 5:49 下午
 */
@Configuration
public class RedisAuthenticationConfig {

    @Bean
    public RedisAuthenticationTokenService redisAuthenticationTokenService() {
        return new RedisAuthenticationTokenService();
    }
}
