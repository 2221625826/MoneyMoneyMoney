package org.money.model.po.request;

import lombok.Data;

import javax.validation.constraints.Min;

/**
 * @author zhangyiheng03
 * @since 2022/8/15 15:29
 */
@Data
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
    private Integer year;

    /**
     * 月份
     */
    private Integer month;

    /**
     * 日
     */
    private Integer day;
}