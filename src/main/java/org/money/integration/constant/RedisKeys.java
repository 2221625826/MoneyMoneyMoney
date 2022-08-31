package org.money.integration.constant;

/**
 * @author zhangyiheng03
 * @since 2022/8/16 15:33
 */
public class RedisKeys {

    public static final String CATEGORY_KEY = "categories";
    public static String genUserMoneyListKey(long userId, int year, int month) {
        return String.format("money_list_%d_%d_%d", userId, year, month);
    }
}