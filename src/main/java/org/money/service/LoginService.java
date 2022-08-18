package org.money.service;

import org.money.model.vo.UserInfoVO;

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

    /**
     * 编辑用户信息
     * @param userInfoVO 新的用户信息
     * @return 更新后的用户信息
     */
    UserInfoVO editUserInfo(UserInfoVO userInfoVO);

    /**
     * 获取用户信息
     * @return 用户信息
     */
    UserInfoVO getUserInfo(Long userId);
}