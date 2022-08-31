package org.money.model.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

import org.money.model.vo.UserInfoVO;

/**
 * @author zhangyiheng03
 * @since 2022/8/31 14:46
 */
@Getter
public class RegisterRequest {
    /**
     * 用户名
     */
    @NotNull(message = "用户名为空")
    String username;

    /**
     * 密码
     */
    @NotNull(message = "密码为空")
    String password;

    /**
     * 用户信息
     */
    UserInfoVO userInfo;
}