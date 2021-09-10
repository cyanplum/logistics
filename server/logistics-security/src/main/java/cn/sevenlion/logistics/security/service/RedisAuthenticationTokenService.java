package cn.sevenlion.logistics.security.service;

import cn.sevenlion.logistics.security.model.token.SevenlionAuthenticationToken;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/20 11:15 上午
 */
public class RedisAuthenticationTokenService implements AuthenticationTokenService {
    @Override
    public String createToken(SevenlionAuthenticationToken authenticationToken) {
        return null;
    }

    @Override
    public SevenlionAuthenticationToken readToken(String tokenStr) {
        return null;
    }
}
