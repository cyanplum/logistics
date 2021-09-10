package cn.sevenlion.logistics.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/19 4:51 下午
 */
@ConfigurationProperties("sevenlion.security")
public class SevenlionSecurityProperties {

    private Class<Object> injectClass;

    private Integer accessTokenExpireIn;

    private ImageValidateCodeProperties imageCode = new ImageValidateCodeProperties();

    private String name;


    public Class<Object> getInjectClass() {
        return injectClass;
    }

    public void setInjectClass(Class<Object> injectClass) {
        this.injectClass = injectClass;
    }

    public Integer getAccessTokenExpireIn() {
        return accessTokenExpireIn;
    }

    public void setAccessTokenExpireIn(Integer accessTokenExpireIn) {
        this.accessTokenExpireIn = accessTokenExpireIn;
    }


    public ImageValidateCodeProperties getImageCode() {
        return imageCode;
    }

    public void setImageCode(ImageValidateCodeProperties imageCode) {
        this.imageCode = imageCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
