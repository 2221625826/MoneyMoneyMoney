package org.money.service;

/**
 * 登录
 * @author zhangyiheng03
 * @since 2022/6/27 15:12
 */

public interface LoginService {
    /**
     * 用户名密码登录
     * @param username 用户名
     * @param password 密码
     * @return 登录token
     */
    String login(String username, String password);

    /**
     * 用户注册
     * @param username 用户名
     * @param password 密码
     * @return 登录token
     */
    String register(String username, String password);
}