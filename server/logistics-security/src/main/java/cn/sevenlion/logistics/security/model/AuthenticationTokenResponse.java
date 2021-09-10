package cn.sevenlion.logistics.security.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/19 5:01 下午
 *
 * 登录返回的对象
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthenticationTokenResponse {

    private String token;

    private Integer tokenExpireIn;

    private Map<String,Object> additionalInfo;

    public AuthenticationTokenResponse() {
    }

    public AuthenticationTokenResponse(String token, Integer tokenExpireIn, Map<String, Object> additionalInfo) {
        this.token = token;
        this.tokenExpireIn = tokenExpireIn;
        this.additionalInfo = additionalInfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getTokenExpireIn() {
        return tokenExpireIn;
    }

    public void setTokenExpireIn(Integer tokenExpireIn) {
        this.tokenExpireIn = tokenExpireIn;
    }

    public Map<String, Object> getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(Map<String, Object> additionalInfo) {
        this.additionalInfo = additionalInfo;
    }


}
