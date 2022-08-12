package org.money.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhangyiheng03
 * @since 2022/5/18 15:03
 */
@AllArgsConstructor
@Getter
public enum HttpCode {
    SUCCESS(200, "成功"),
    DEFAULT_ERROR(400, "系统错误"),
    NEED_LOGIN(401, "需要登录"),
    NOT_FOUNT(404, "找不到资源"),
    SERVICE_ERROR(500, "服务错误"),
    PARAM_ERROR(501, "参数错误");

    private final int code;

    private final String msg;

}