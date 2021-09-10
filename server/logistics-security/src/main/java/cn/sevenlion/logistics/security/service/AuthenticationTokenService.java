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
 * @date 2021/4/19 4:08 下午
 */
public interface AuthenticationTokenService {

    /**
     * 创建token
     * @param authenticationToken
     * @return
     */
    public String createToken(SevenlionAuthenticationToken authenticationToken);

    /**
     * 读取token
     * @param tokenStr
     * @return
     */
    public SevenlionAuthenticationToken readToken(String tokenStr);

}
