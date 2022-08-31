package org.money.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author zhangyiheng03
 * @since 2022/8/31 11:13
 */

public class MoneyUtils {

    public static final BigDecimal HUNDRED = new BigDecimal(100);

    public static final BigDecimal ZERO = new BigDecimal(0);

    public static BigDecimal longToBigDecimal(Long amount) {
        return new BigDecimal(amount).divide(HUNDRED,2, RoundingMode.HALF_UP);
    }

    public static Long bigDecimalToLong(BigDecimal amount) {
        return amount.multiply(HUNDRED).longValue();
    }
}