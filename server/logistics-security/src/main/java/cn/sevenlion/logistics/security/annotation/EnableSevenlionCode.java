package cn.sevenlion.logistics.security.annotation;

import org.springframework.context.annotation.Import;
import cn.sevenlion.logistics.security.config.SevenlionValidateCodeConfig;
import cn.sevenlion.logistics.security.enums.CodeServiceEnum;
import cn.sevenlion.logistics.security.selector.SevenlionImageCodeSelector;

import java.lang.annotation.*;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/21 11:28 上午
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({SevenlionValidateCodeConfig.class, SevenlionImageCodeSelector.class})
public @interface EnableSevenlionCode {

    CodeServiceEnum value() default CodeServiceEnum.IMAGE;
}
