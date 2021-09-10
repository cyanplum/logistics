package cn.sevenlion.logistics.security.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import cn.sevenlion.logistics.security.annotation.EnableSevenlionSecurity;
import cn.sevenlion.logistics.security.enums.TokenServiceEnum;
import cn.sevenlion.logistics.security.service.JwtAuthenticationConfig;
import cn.sevenlion.logistics.security.service.RedisAuthenticationConfig;

import java.util.Map;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/20 11:00 上午
 */
public class SevenlionSecurityTokenSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        Map<String, Object> annotationMaps = annotationMetadata.getAnnotationAttributes(EnableSevenlionSecurity.class.getName());
        Object service = annotationMaps.get("value");
        if (service instanceof TokenServiceEnum) {
            service = (TokenServiceEnum) service;
            if (service == TokenServiceEnum.JWT) {
                return new String[]{JwtAuthenticationConfig.class.getName()};
            }
            if (service == TokenServiceEnum.REDIS) {
                return new String[]{RedisAuthenticationConfig.class.getName()};
            }
        }
        return new String[0];
    }
}
