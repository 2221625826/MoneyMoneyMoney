package org.money.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhangyiheng03
 * @since 2022/8/31 9:54
 */

@Data
@AllArgsConstructor
public class AnalysePieResponse {

    /**
     * 项名称
     */
    private String name;

    /**
     * 值
     */
    private BigDecimal value;
}