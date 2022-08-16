package org.money.web.interceptor;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import org.money.model.enums.HttpCode;
import org.money.util.CookieUtils;
import org.money.util.JWTUtils;
import org.money.web.UserContext;

/**
 * 身份鉴权校验
 * @author zhangyiheng03
 * @since 2022/6/14 16:53
 */
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        try {
            String token = CookieUtils.getCookie(CookieUtils.LOGIN_TOKEN, request);
            if (StringUtils.isBlank(token)) {
                response.setStatus(HttpCode.NEED_LOGIN.getCode());
                return false;
            }
            if (JWTUtils.checkToken(token)) {
                Long userId = JWTUtils.getUserIdFromToken(token);
                UserContext.setUserId(userId);
            } else {
                response.setStatus(HttpCode.NEED_LOGIN.getCode());
                return false;
            }
        } catch (Exception e) {
            log.error("[op:preHandle] catch-exception,", e);
        }
        return true;
    }

    @Override
    public void postHandle(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull Object handler, ModelAndView modelAndView) {
        UserContext.remove();
    }
}