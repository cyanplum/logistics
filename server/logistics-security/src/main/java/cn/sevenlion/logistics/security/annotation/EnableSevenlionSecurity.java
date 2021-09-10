package cn.sevenlion.logistics.security.annotation;

import org.springframework.context.annotation.Import;
import cn.sevenlion.logistics.security.config.SevenlionSecurityConfig;
import cn.sevenlion.logistics.security.config.SevenlionSecurityMethodConfiguration;
import cn.sevenlion.logistics.security.enums.TokenServiceEnum;
import cn.sevenlion.logistics.security.selector.SevenlionSecurityTokenSelector;

import java.lang.annotation.*;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/20 10:53 上午
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({SevenlionSecurityConfig.class, SevenlionSecurityTokenSelector.class, SevenlionSecurityMethodConfiguration.class})
public @interface EnableSevenlionSecurity {

    TokenServiceEnum value() default TokenServiceEnum.JWT;

}
