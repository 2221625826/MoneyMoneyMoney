package org.money;

import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.money.util.DateTimeUtils;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zhangyiheng03
 * @since 2022/8/17 9:16
 */
@SpringBootTest
public class UtilsTest {

    @Test
    public void dateTimeTest() {
        System.out.println(DateTimeUtils.parseLongToString(0L, DateTimeUtils.YEAR_MONTH_DAY_FORMAT));
        System.out.println(DateTimeUtils.parseStringToLong("2022年08月18日", DateTimeUtils.YEAR_MONTH_DAY_FORMAT));
    }
}