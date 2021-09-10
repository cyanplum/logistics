package cn.sevenlion.logistics.security.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import cn.sevenlion.logistics.security.model.UserDetails;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/19 3:52 下午
 */
public interface AuthenticationService<T> {

    /**
     * 登录逻辑处理
     * @param body
     * @param passwordEncoder
     * @return
     */
    public UserDetails loadUser(T body, PasswordEncoder passwordEncoder);
}
