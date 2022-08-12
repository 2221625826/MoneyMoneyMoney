package org.money.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie 工具类
 *
 * @author zhangyiheng03
 * @since 2022/6/24 14:35
 */

public class CookieUtils {

    public static final int MAG_AGE = -1;

    public static final String LOGIN_TOKEN = "MoneyMoneyMoney-token";

    /**
     * 获取指定cookie
     * @param name cookie名
     * @param request http请求
     * @return cookie
     */
    public static String getCookie(String name, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie: cookies) {
            if (cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    /**
     * 设置cookie
     * @param name 名
     * @param value 值
     * @param maxAge 生存周期
     * @param response 响应
     */
    public static void setCookie(String name, String value, int maxAge,
                                 HttpServletResponse response) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}