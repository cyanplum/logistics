package cn.sevenlion.logistics.security.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import cn.sevenlion.logistics.security.common.UrlMatcherRegistry;
import cn.sevenlion.logistics.security.common.UrlStrategyMatcher;
import cn.sevenlion.logistics.security.exceptions.SevenlionException;
import cn.sevenlion.logistics.security.model.token.SevenlionAuthenticationToken;
import cn.sevenlion.logistics.security.properties.SevenlionSecurityProperties;
import cn.sevenlion.logistics.security.service.AuthenticationTokenService;
import cn.sevenlion.logistics.security.service.JwtToRedisTokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/19 5:32 下午
 * 校验url,对token进行校验授权
 */
public class AuthenticationProcessingFilter extends OncePerRequestFilter implements InitializingBean {

    Logger logger = LoggerFactory.getLogger(AuthenticationProcessingFilter.class);

    PathMatcher pathMatcher = new AntPathMatcher();

    @Autowired(required = false)
    List<UrlStrategyMatcher> urlStrategyMatchers;

    UrlMatcherRegistry urlMatcherRegistry;

    public AuthenticationProcessingFilter() {
    }

    @Autowired
    SevenlionSecurityProperties properties;

    @Autowired(required = false)
    AuthenticationTokenService authenticationTokenService;

    @Autowired
    AuthenticationEventPublisher authenticationEventPublisher;

    @Autowired
    private SevenlionAuthenticationEntryPoint sevenlionAuthenticationEntryPoint;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private JwtToRedisTokenService jwtToRedisTokenService;
    
    
    
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            boolean checkUrl = checkUrl(httpServletRequest);
            if (!checkUrl) {
                String tokenStr = readRequestToken(httpServletRequest);
                String redisToken = jwtToRedisTokenService.readToken(tokenStr);
                if (redisToken == null) {
                    throw new ServletException("登录过期或失效！");
                }
                SevenlionAuthenticationToken sevenlionAuthenticationToken = authenticationTokenService.readToken(redisToken);
                if (sevenlionAuthenticationToken == null) {
                    throw new ServletException("登录过期或失效！");
                }
                logger.debug("authentication success:" + sevenlionAuthenticationToken.getAuthenticationUser().getId());
                SecurityContextHolder.getContext().setAuthentication(sevenlionAuthenticationToken.getAuthenticationUser());
            }else {
                SecurityContextHolder.clearContext();
            }
        }catch (Exception e) {
            SecurityContextHolder.clearContext();
            authenticationEventPublisher.publishAuthenticationFailure(new BadCredentialsException(e.getMessage()),
                    new PreAuthenticatedAuthenticationToken("token", "N/A"));
            if (authenticationFailureHandler != null) {
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, new BadCredentialsException(e.getMessage(), e));
            }
            sevenlionAuthenticationEntryPoint.commence(httpServletRequest,httpServletResponse,new BadCredentialsException(e.getMessage(), e));
            return;
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    public boolean checkUrl(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        List<String> ignoreUrls = urlMatcherRegistry.getIgnoreUrls();
        List<String> matchUrls = urlMatcherRegistry.getMatchUrls();
        if (ignoreUrls != null && !ignoreUrls.isEmpty()) {
            for (String ignoreUrl : ignoreUrls) {
                if(pathMatcher.match(ignoreUrl,requestURI)){
                    return true;
                }
            }
        }
        return false;
    }

    public String readRequestToken(HttpServletRequest request) {
        String headerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (headerToken == null) {
            throw new SevenlionException("受保护的资源！");
        }
        if (headerToken.isEmpty() || !headerToken.startsWith("Bearer ")) {
            throw new SevenlionException("未找到token或token格式不正确");
        }
        String tokenStr = headerToken.substring(7);
        return tokenStr;
    }

    @Override
    public void afterPropertiesSet() {
        UrlMatcherRegistry urlMatcherRegistry = new UrlMatcherRegistry();
        if (urlStrategyMatchers != null && !urlStrategyMatchers.isEmpty()) {
            for (UrlStrategyMatcher it : urlStrategyMatchers) {
                it.handleUrl(urlMatcherRegistry);
            }
        }
        this.urlMatcherRegistry = urlMatcherRegistry;
    }
}
