package cn.sevenlion.logistics.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/7/12 4:43 下午
 */
@Data
@ConfigurationProperties(prefix = "cyanplum.swagger")
@Configuration
public class SwaggerProperties {

    private String scanUrl;

    private boolean enable;

    private String title;

    private String description;

    private String version;
}
