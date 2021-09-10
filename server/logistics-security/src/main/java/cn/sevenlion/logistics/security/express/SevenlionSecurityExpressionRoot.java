package cn.sevenlion.logistics.security.express;

import cn.sevenlion.logistics.security.model.token.SevenlionAuthenticationUser;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.expression.SecurityExpressionOperations;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/20 6:19 下午
 *
 * 自定义权限校验的方法
 */
public class SevenlionSecurityExpressionRoot implements SecurityExpressionOperations, MethodSecurityExpressionOperations {

    private Object target;

    private Object filterTarget;

    private Object returnTarget;

    private AuthenticationTrustResolver trustResolver;

    private RoleHierarchy roleHierarchy;

    private PermissionEvaluator permissionEvaluator;

    private Authentication authentication;

    private String rolePrefix;

    public SevenlionSecurityExpressionRoot(Authentication authentication) {
        this.authentication = authentication;
    }

    public SevenlionSecurityExpressionRoot() {
    }

    @Override
    public Authentication getAuthentication() {
        return authentication;
    }

    @Override
    public boolean hasAuthority(String s) {
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (authority.getAuthority().equals(s)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasAnyAuthority(String... strings) {
        for (String string : strings) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if (authority.getAuthority().equals(string)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasRole(String s) {
        if (authentication instanceof SevenlionAuthenticationUser) {
            if (((SevenlionAuthenticationUser) authentication).getRoles() != null) {
                if (((SevenlionAuthenticationUser) authentication).getRoles().contains(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasAnyRole(String... strings) {
        if (authentication instanceof SevenlionAuthenticationUser) {
            if (((SevenlionAuthenticationUser) authentication).getRoles() != null) {
                for (String string : strings) {
                    if (((SevenlionAuthenticationUser) authentication).getRoles().contains(string)) {
                        return true;
                    }
                }
            }else {
                return false;
            }
        }
        return hasAnyAuthority(strings);
    }

    @Override
    public boolean permitAll() {
        return true;
    }

    @Override
    public boolean denyAll() {
        return false;
    }

    @Override
    public boolean isAnonymous() {
        return trustResolver.isRememberMe(authentication);
    }

    @Override
    public boolean isAuthenticated() {
        return !isAnonymous();
    }

    @Override
    public boolean isRememberMe() {
        return trustResolver.isRememberMe(authentication);
    }

    @Override
    public boolean isFullyAuthenticated() {
        return !trustResolver.isAnonymous(authentication) && !trustResolver.isRememberMe(authentication);
    }

    @Override
    public boolean hasPermission(Object target, Object permission) {
        return permissionEvaluator.hasPermission(authentication,target,permission);
    }

    @Override
    public boolean hasPermission(Object targetId, String targetType, Object permission) {
        return permissionEvaluator.hasPermission(authentication,(Serializable) target,targetType,permission);
    }

    @Override
    public void setFilterObject(Object o) {
        this.filterTarget = o;
    }

    @Override
    public Object getFilterObject() {
        return filterTarget;
    }

    @Override
    public void setReturnObject(Object o) {
        returnTarget = o;
    }

    @Override
    public Object getReturnObject() {
        return returnTarget;
    }

    @Override
    public Object getThis() {
        return target;
    }

    public boolean hasPermission(String permission) {
        if (authentication instanceof SevenlionAuthenticationUser) {
            if (((SevenlionAuthenticationUser) authentication).getPermissions() != null) {
                if (((SevenlionAuthenticationUser) authentication).getPermissions().contains(permission)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Object getFilterTarget() {
        return filterTarget;
    }

    public void setFilterTarget(Object filterTarget) {
        this.filterTarget = filterTarget;
    }

    public Object getReturnTarget() {
        return returnTarget;
    }

    public void setReturnTarget(Object returnTarget) {
        this.returnTarget = returnTarget;
    }

    public AuthenticationTrustResolver getTrustResolver() {
        return trustResolver;
    }

    public void setTrustResolver(AuthenticationTrustResolver trustResolver) {
        this.trustResolver = trustResolver;
    }

    public RoleHierarchy getRoleHierarchy() {
        return roleHierarchy;
    }

    public void setRoleHierarchy(RoleHierarchy roleHierarchy) {
        this.roleHierarchy = roleHierarchy;
    }

    public PermissionEvaluator getPermissionEvaluator() {
        return permissionEvaluator;
    }

    public void setPermissionEvaluator(PermissionEvaluator permissionEvaluator) {
        this.permissionEvaluator = permissionEvaluator;
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    public String getRolePrefix() {
        return rolePrefix;
    }

    public void setRolePrefix(String rolePrefix) {
        this.rolePrefix = rolePrefix;
    }
}
