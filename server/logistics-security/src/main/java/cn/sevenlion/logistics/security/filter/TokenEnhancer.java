package cn.sevenlion.logistics.security.filter;

import cn.sevenlion.logistics.security.model.token.SevenlionAuthenticationUser;

import java.util.Map;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/19 4:44 下午
 */
public interface TokenEnhancer {

    /**
     * 增强登录
     * @param authenticationToken
     * @return
     */
    public Map<String,Object> enhance(SevenlionAuthenticationUser authenticationToken);
}
