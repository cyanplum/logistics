package cn.sevenlion.logistics.security.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import cn.sevenlion.logistics.security.exceptions.SevenlionException;
import cn.sevenlion.logistics.security.model.token.SevenlionAuthenticationUser;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/21 11:11 上午
 *
 * 解析UserInfo在访问controller的时候将UserInfo对象注入进去
 */
public class SevenlionSecurityWebArgumentResolver implements HandlerMethodArgumentResolver {

    private Class injectClass;

    private ObjectMapper objectMapper;

    public SevenlionSecurityWebArgumentResolver(Class injectClass, ObjectMapper objectMapper) {
        this.injectClass = injectClass;
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        if (injectClass == null) {
            return false;
        }
        if (methodParameter.getParameterType().isAssignableFrom(Authentication.class)) {
            return true;
        }
        return methodParameter.getParameterType().isAssignableFrom(injectClass);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        Class clazz = methodParameter.getParameterType();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof SevenlionAuthenticationUser) {
            if (clazz.isAssignableFrom(Authentication.class)) {
                return objectMapper.convertValue(authentication, clazz);
            }
            String detail = ((SevenlionAuthenticationUser) authentication).getUserDetail();
            try{
                Object result = objectMapper.readValue(detail, clazz);
                return result;
            }catch (Exception e) {
            }
            return null;
        } else {
            throw new SevenlionException("authentication 参数错误!");
        }
    }
}
