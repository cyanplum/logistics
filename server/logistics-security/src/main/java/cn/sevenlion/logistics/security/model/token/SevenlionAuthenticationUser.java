package cn.sevenlion.logistics.security.model.token;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/19 4:11 下午
 *
 * 自定义校验对象
 */
public class SevenlionAuthenticationUser implements Authentication {

    private Long id;

    private List<String> roles;

    private List<String> permissions;

    private String userDetail;

    public SevenlionAuthenticationUser(Long id, List<String> roles, List<String> permissions, String userDetail) {
        this.id = id;
        this.roles = roles;
        this.permissions = permissions;
        this.userDetail = userDetail;
    }

    public SevenlionAuthenticationUser() {
    }

    @Override
    public Collection getAuthorities() {
        if (this.permissions.isEmpty()) {
            return null;
        }
        return AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils
                .collectionToCommaDelimitedString(permissions));
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return id;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {

    }

    @Override
    public  String getName() {
        return id.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public String getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(String userDetail) {
        this.userDetail = userDetail;
    }
}
