package org.money.model.request;

import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author zhangyiheng03
 * @since 2022/8/15 15:29
 */
@Getter
public class MoneyListRequest {
    /**
     * 页码
     */
    @Min(value = 0)
    private Integer page;

    /**
     * 页号
     */
    @Min(value = 0)
    private Integer pageSize;

    /**
     * 年份
     */
    @NotNull
    private Integer year;

    /**
     * 月份
     */
    @NotNull
    private Integer month;
}