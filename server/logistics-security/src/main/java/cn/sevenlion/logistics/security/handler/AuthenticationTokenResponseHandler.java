package cn.sevenlion.logistics.security.handler;


import cn.sevenlion.logistics.security.response.CommonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import cn.sevenlion.logistics.security.filter.TokenEnhancer;
import cn.sevenlion.logistics.security.model.AuthenticationTokenResponse;
import cn.sevenlion.logistics.security.model.token.SevenlionAuthenticationToken;
import cn.sevenlion.logistics.security.model.token.SevenlionAuthenticationUser;
import cn.sevenlion.logistics.security.properties.SevenlionSecurityProperties;
import cn.sevenlion.logistics.security.service.AuthenticationTokenService;
import cn.sevenlion.logistics.security.service.JwtToRedisTokenService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
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
 * @date 2021/4/19 4:06 下午
 *
 * 对登录方法的加强，执行里面的enhance方法
 * 然后执行对应的创建token方法
 */
public class AuthenticationTokenResponseHandler {

    private ObjectMapper objectMapper;

    @Autowired(required = false)
    private AuthenticationTokenService authenticationTokenService;

    @Autowired(required = false)
    private List<TokenEnhancer> tokenEnhancerList;

    @Autowired
    private SevenlionSecurityProperties sevenlionSecurityProperties;

    @Autowired
    private JwtToRedisTokenService jwtToRedisTokenService;

    public AuthenticationTokenResponseHandler() {
    }

    public AuthenticationTokenResponseHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void responseWithToken(HttpServletRequest request, HttpServletResponse response, Authentication authenticationUser) throws IOException {
        //对登录方法的加强
        SevenlionAuthenticationToken token = buildAuthenticationToken((SevenlionAuthenticationUser) authenticationUser);
        String tokenStr = authenticationTokenService.createToken(token);
        //jwt token 转redis
        String redisToken = jwtToRedisTokenService.createToken(tokenStr,(SevenlionAuthenticationUser) authenticationUser);
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());
        AuthenticationTokenResponse tokenResponse = new AuthenticationTokenResponse(redisToken,token.getExpireIn(),token.getAdditionalInfo());
        response.getWriter().write(objectMapper.writeValueAsString(CommonResult.success(tokenResponse)));
        response.getWriter().flush();
    }

    private SevenlionAuthenticationToken buildAuthenticationToken(SevenlionAuthenticationUser authenticationUser) {
        Map<String,Object> map = new HashMap<>(16);
        if (tokenEnhancerList != null && !tokenEnhancerList.isEmpty()) {
            //执行对登录增强的的enhance的调用
            tokenEnhancerList.forEach(it -> map.putAll(it.enhance(authenticationUser)));
            return new SevenlionAuthenticationToken(authenticationUser,sevenlionSecurityProperties.getAccessTokenExpireIn(),map);
        }
        return new SevenlionAuthenticationToken(authenticationUser,sevenlionSecurityProperties.getAccessTokenExpireIn());
    }


}
