package cn.sevenlion.logistics.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.GenericFilterBean;
import cn.sevenlion.logistics.security.annotation.LoginMapping;
import cn.sevenlion.logistics.security.exceptions.SevenlionException;
import cn.sevenlion.logistics.security.handler.AuthenticationTokenResponseHandler;
import cn.sevenlion.logistics.security.model.image.ImageValidateCodeBean;
import cn.sevenlion.logistics.security.model.token.CheckAuthenticationToken;
import cn.sevenlion.logistics.security.reposiroty.AbstractValidateCodeService;
import cn.sevenlion.logistics.security.service.AuthenticationService;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/19 3:51 下午
 * 对请求做一个过滤，判断是否是登录请求
 * 如果是登录请求进行操作
 * 如果不是登录请求放行
 */
public class SevenlionAuthenticationFilter extends GenericFilterBean implements InitializingBean {


    private AuthenticationManager authenticationManager;

    private AuthenticationSuccessHandler authenticationSuccessHandler;

    private AuthenticationFailureHandler authenticationFailureHandler;

    private ObjectMapper objectMapper;

    public SevenlionAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationSuccessHandler authenticationSuccessHandler, AuthenticationFailureHandler authenticationFailureHandler, ObjectMapper objectMapper) {
        this.authenticationManager = authenticationManager;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.objectMapper = objectMapper;
    }

    private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource = new WebAuthenticationDetailsSource();

    @Autowired(required = false)
    private List<AuthenticationService> authenticationServices;

    @Autowired
    private AuthenticationEventPublisher authenticationEventPublisher;

    @Autowired
    private AuthenticationTokenResponseHandler authenticationTokenResponseHandler;

    @Autowired
    private SevenlionAuthenticationEntryPoint sevenlionAuthenticationEntryPoint;

    @Autowired(required = false)
    private Map<String, AbstractValidateCodeService> abstractValidateCodeServices;

    private List<RequestMatcher> requiresAuthenticationRequestMatchers = null;
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            try{
                if (httpRequest.getMethod().equals("OPTIONS") || !checkLoginUrl(httpRequest)) {
                    filterChain.doFilter(request, response);
                    return;
                }
                if (!httpRequest.getMethod().equals("POST")) {
                    throw new SevenlionException("authentication http method not supported");
                }
                AuthenticationService service = findService(httpRequest);
                //获取接口的方法实现
                Type genericInterface = service.getClass().getGenericInterfaces()[0];
                //得到方法中的参数类型
                Class formClass = (Class) ((ParameterizedTypeImpl) genericInterface).getActualTypeArguments()[0];
                //解析请求表单
                Object body = parseForm(httpRequest, formClass);

                //验证码验证
                if (body instanceof ImageValidateCodeBean) {
                    if (abstractValidateCodeServices == null || abstractValidateCodeServices.isEmpty()) {
                        throw new SevenlionException("请检查验证码服务是否未开启");
                    }
                    for (String key : abstractValidateCodeServices.keySet()) {
                        abstractValidateCodeServices.get(key).validate(((ImageValidateCodeBean) body).getImageCode(),((ImageValidateCodeBean) body).getDeviceId());
                    }
                }
                //组装token，给manager进行校验
                CheckAuthenticationToken authenticationToken = new CheckAuthenticationToken(null,body,false,formClass);
                authenticationToken.setDetails(authenticationDetailsSource.buildDetails(httpRequest));
                //manager校验，调用authenticationProvider的校验
                Authentication authenticate = authenticationManager.authenticate(authenticationToken);
                //成功的处理
                success(authenticate,httpRequest,httpResponse,false);
            }catch (Exception e) {
                authenticationEventPublisher.publishAuthenticationFailure(new BadCredentialsException(e.getMessage()),
                        new PreAuthenticatedAuthenticationToken("access-token", "N/A"));
                if (authenticationFailureHandler != null) {
                    authenticationFailureHandler.onAuthenticationFailure(httpRequest, httpResponse, new BadCredentialsException(e.getMessage(), e));
                }
                sevenlionAuthenticationEntryPoint.commence(httpRequest,httpResponse,new BadCredentialsException(e.getMessage(), e));
            }
        }
    }

    private boolean checkLoginUrl(HttpServletRequest httpRequest) {
        if (requiresAuthenticationRequestMatchers == null) {
            return false;
        }
        for (RequestMatcher it : requiresAuthenticationRequestMatchers) {
            if (it.matches(httpRequest)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 找到加了登录注解的service服务
     * @param request
     * @return
     */
    public AuthenticationService findService(HttpServletRequest request) {
        AuthenticationService authenticationService = null;
        for (AuthenticationService service : authenticationServices) {
            LoginMapping annotation = AnnotationUtils.findAnnotation(service.getClass(), LoginMapping.class);
            if (annotation != null) {
                String value = annotation.value();
                //判断当前登录服务url是否和请求url相同，因为可能有多个登录服务
                if (value.trim().equals(request.getRequestURI())) {
                    authenticationService = service;
                    break;
                }
            }
        }
        if (authenticationService == null) {
            throw new SevenlionException("service 错误！");
        }
        return authenticationService;
    }


    private <T> T parseForm(HttpServletRequest httpRequest, Class<T> formClass) throws IOException {
        return objectMapper.readValue(httpRequest.getInputStream(),formClass);
    }


    /**
     * 登录成功将授权给spring security,然后返回信息
     * @param authentication
     * @param request
     * @param response
     * @param isRefresh
     * @throws IOException
     */
    private void success(Authentication authentication, HttpServletRequest request, HttpServletResponse response, boolean isRefresh) throws IOException {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        authenticationEventPublisher.publishAuthenticationSuccess(authentication);
        authenticationTokenResponseHandler.responseWithToken(request,response, authentication);
    }

    @Override
    public void afterPropertiesSet() {
       setAuthFilterProcess();
    }

    /**
     * 实现了登录接口的服务，添加url匹配
     */
    private void setAuthFilterProcess() {
        if (authenticationServices == null ||authenticationServices.isEmpty()) {
            return;
            //throw new SevenlionException("服务错误！");
        }
        List<String> urls = new ArrayList<>();
        List<RequestMatcher> requestMatchers = new ArrayList<>();
        for (AuthenticationService it : authenticationServices) {
            LoginMapping annotation = AnnotationUtils.findAnnotation(it.getClass(), LoginMapping.class);
            if (annotation != null) {
                urls.add(annotation.value());
                requestMatchers.add(new AntPathRequestMatcher(annotation.value()));
            }
        }
        if (urls.isEmpty()) {
            throw new SevenlionException("登录url不能为空");
        }
        this.requiresAuthenticationRequestMatchers = requestMatchers;
    }
}
