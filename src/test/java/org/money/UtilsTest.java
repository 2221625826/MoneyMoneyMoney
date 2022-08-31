package org.money;

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
        System.out.println(DateTimeUtils.getDaysOfMonth(2022, 2));
    }
}