package cn.sevenlion.logistics.security.model.token;

import java.io.Serializable;
import java.util.Map;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/19 4:10 下午
 */
public class SevenlionAuthenticationToken implements Serializable {

    private SevenlionAuthenticationUser authenticationUser;

    private Integer expireIn;

    private Map<String,Object> additionalInfo;


    public SevenlionAuthenticationToken() {
    }

    public SevenlionAuthenticationToken(SevenlionAuthenticationUser authenticationUser, Integer expireIn) {
        this.authenticationUser = authenticationUser;
        this.expireIn = expireIn;
    }

    public SevenlionAuthenticationToken(SevenlionAuthenticationUser authenticationUser, Integer expireIn, Map<String, Object> additionalInfo) {
        this.authenticationUser = authenticationUser;
        this.expireIn = expireIn;
        this.additionalInfo = additionalInfo;
    }

    public SevenlionAuthenticationUser getAuthenticationUser() {
        return authenticationUser;
    }

    public void setAuthenticationUser(SevenlionAuthenticationUser authenticationUser) {
        this.authenticationUser = authenticationUser;
    }

    public Integer getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(Integer expireIn) {
        this.expireIn = expireIn;
    }

    public Map<String, Object> getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(Map<String, Object> additionalInfo) {
        this.additionalInfo = additionalInfo;
    }


}
