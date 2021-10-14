package cn.sevenlion.logistics.user.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/9/19 1:06 上午
 */
@Data
@ConfigurationProperties(prefix = "wechat", ignoreInvalidFields = true)
public class WechatProperties {

    private String appId;

    private String secret;

    private String token = "";

    private String aesKey = "";

    private String msgDataFormat = "";
}
