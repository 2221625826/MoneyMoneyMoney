package org.money.web;

/**
 * @author zhangyiheng03
 * @since 2022/6/24 15:38
 */
public class UserContext {
    private static final ThreadLocal<Long> userId = new ThreadLocal<>();

    public static void remove() {
        userId.remove();
    }

    public static void setUserId(Long userId) {
        UserContext.userId.set(userId);
    }

    public static Long getUserId() {
        return userId.get();
    }
}