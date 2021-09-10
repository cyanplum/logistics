package cn.sevenlion.logistics.security.common;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import cn.sevenlion.logistics.security.exceptions.SevenlionException;
import cn.sevenlion.logistics.security.model.UserDetails;
import cn.sevenlion.logistics.security.model.token.CheckAuthenticationToken;
import cn.sevenlion.logistics.security.model.token.SevenlionAuthenticationUser;
import cn.sevenlion.logistics.security.properties.SevenlionSecurityProperties;
import cn.sevenlion.logistics.security.service.AuthenticationService;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/20 12:31 下午
 */
public class SecurityAuthenticationProvider implements AuthenticationProvider {

    private List<AuthenticationService> authenticationServices;

    private PasswordEncoder passwordEncoder;

    private ObjectMapper objectMapper;

    private SevenlionSecurityProperties properties;

    public SecurityAuthenticationProvider() {
    }

    public SecurityAuthenticationProvider(List<AuthenticationService> authenticationServices, PasswordEncoder passwordEncoder, ObjectMapper objectMapper, SevenlionSecurityProperties properties) {
        this.authenticationServices = authenticationServices;
        this.passwordEncoder = passwordEncoder;
        this.objectMapper = objectMapper;
        this.properties = properties;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        CheckAuthenticationToken finalAuthentication = (CheckAuthenticationToken) authentication;
        AuthenticationService authenticationService = authenticationServices.stream().filter(it -> {
            return checkAuthenticationServices(it.getClass(), finalAuthentication);
        }).collect(Collectors.toList()).get(0);
        Method declaredMethod = AuthenticationService.class.getDeclaredMethods()[0];
        try{
            Object invoke = declaredMethod.invoke(authenticationService, authentication.getPrincipal(), passwordEncoder);
            if (invoke instanceof UserDetails) {
                checkUserDetails((UserDetails) invoke);
                String jsonStr = objectMapper.writeValueAsString(((UserDetails<?>) invoke).getUserDetail());
                SevenlionAuthenticationUser user = new SevenlionAuthenticationUser();
                BeanUtils.copyProperties(invoke,user);
                user.setUserDetail(jsonStr);
                user.setPermissions(((UserDetails<?>) invoke).getPermission());
                user.setRoles(((UserDetails<?>) invoke).getRoles());
                return user;
            }
        }catch (Exception e) {
            e.printStackTrace();
            if (e instanceof InvocationTargetException) {
                String message = ((InvocationTargetException) e).getTargetException().getMessage();
                throw new SevenlionException(message != null ?message:"加载loadUser方法错误");
            }else {
                throw new SevenlionException(e.getMessage());
            }

            //throw new SevenlionException("加载loadUser方法错误");
        }
        return null;
    }

    private void checkUserDetails(UserDetails userDetails) {
        if (userDetails.getId() == null) {
            throw new SevenlionException("id 不能为空!");
        }
        if (userDetails.getEnable() == false) {
            throw new SevenlionException("该账号不可用!");
        }
        if (userDetails.getNonLocked() == false) {
            throw new SevenlionException("该账号被锁定!");
        }
        if (userDetails.getUserDetail() == null) {
            throw new SevenlionException("userDetail为空!");
        }
        if (!userDetails.getUserDetail().getClass().isAssignableFrom(properties.getInjectClass())) {
            throw new SevenlionException("传入注入对象非法,请修改userDetail类型!");
        }

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CheckAuthenticationToken.class.isAssignableFrom(aClass);
    }

    public boolean checkAuthenticationServices(Class clazz,CheckAuthenticationToken authenticationToken) {
        Type[] genericInterfaces = clazz.getGenericInterfaces();
        if (genericInterfaces.length  < 1) {
            return false;
        }
        if (genericInterfaces[0] instanceof ParameterizedTypeImpl) {
            return authenticationToken.getPrincipalClass() == ((ParameterizedTypeImpl) genericInterfaces[0]).getActualTypeArguments()[0];
        }
        return false;
    }


}
