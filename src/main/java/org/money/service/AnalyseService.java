package org.money.service;

import java.util.List;

import org.money.model.response.AnalyseLineResponse;
import org.money.model.response.AnalysePieResponse;

/**
 * @author zhangyiheng03
 * @since 2022/8/31 10:15
 */

public interface AnalyseService {

    /**
     * 按月分析饼图
     * @param year 年份
     * @param month 月份
     * @return 项列表
     */
    List<AnalysePieResponse> getPie(long userId, int year, int month);

    /**
     * 按年分析饼图
     * @param year 年份
     * @return 项列表
     */
    List<AnalysePieResponse> getPie(long userId, int year);

    /**
     * 按月分析折线图
     * @param year 年份
     * @param month 月份
     * @return 项列表 值列表
     */
    AnalyseLineResponse getLine(long userId, int year, int month);

    /**
     * 按年分析折线图
     * @param year 年份
     * @return 项列表 值列表
     */
    AnalyseLineResponse getLine(long userId, int year);
}