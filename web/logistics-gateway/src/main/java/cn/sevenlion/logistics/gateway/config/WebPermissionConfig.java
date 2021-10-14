package cn.sevenlion.logistics.gateway.config;

import cn.dev33.satoken.reactor.context.SaReactorSyncHolder;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/9/10 9:41 下午
 */
@Configuration
public class WebPermissionConfig {

    @Bean
    public SaReactorFilter saReactorFilter() {
        return new SaReactorFilter()
                //拦截的路径
                .addInclude("/**")
                //不拦截的路径
                .addExclude("/favicon.ico",
                        "/webjars/**",
                        "/swagger-ui.html",
                        "/v2/api-docs",
                        "/static/**",
                        "/route/**",
                        "/swagger-resources/**",
                        "/user/register")
                //除了登录，都需要认证
                .setAuth(it-> SaRouter.match("/**","/user/auth/", StpUtil::checkLogin))
                .setError(it -> {
                    // 设置错误返回格式为JSON
                    ServerWebExchange exchange = SaReactorSyncHolder.getContext();
                    exchange.getResponse().getHeaders().set("Content-Type", "application/json; charset=utf-8");
                    return SaResult.error(it.getMessage());
                });
    }
}
