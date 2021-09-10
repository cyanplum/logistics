package cn.sevenlion.logistics.security.model.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/19 3:54 下午
 *
 * 自定义校验的token对象
 */
public class CheckAuthenticationToken extends AbstractAuthenticationToken {

    private Object principal;


    private boolean isRefresh;

    private Class principalClass;

    public CheckAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        super.setAuthenticated(false);
    }

    public CheckAuthenticationToken(Collection<? extends GrantedAuthority> authorities, Object principal, boolean isRefresh, Class principalClass) {
        super(authorities);
        super.setAuthenticated(false);
        this.principal = principal;
        this.isRefresh = isRefresh;
        this.principalClass = principalClass;
    }


    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    public void setPrincipal(Object principal) {
        this.principal = principal;
    }

    public boolean isRefresh() {
        return isRefresh;
    }

    public void setRefresh(boolean refresh) {
        isRefresh = refresh;
    }

    public Class getPrincipalClass() {
        return principalClass;
    }

    public void setPrincipalClass(Class principalClass) {
        this.principalClass = principalClass;
    }
}
