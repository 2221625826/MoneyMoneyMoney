package org.money.model.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhangyiheng03
 * @since 2022/8/22 8:37
 */
@Data
public class MoneySumResponse {
    /**
     * 收入
     */
    BigDecimal in;

    /**
     * 支出
     */
    BigDecimal out;
}