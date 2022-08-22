package org.money.service;

import java.util.List;

import org.money.model.common.PageResult;
import org.money.model.common.Pagination;
import org.money.model.response.MoneySumResponse;
import org.money.model.vo.MoneyRecordVO;

/**
 * 账单相关接口
 * @author zhangyiheng03
 * @since 2022/8/16 13:53
 */

public interface MoneyService {
    /**
     * 按月列出账单
     * @param pagination 分页参数
     * @param year 年份
     * @param month 月份
     * @return 账单列表
     */
     PageResult<MoneyRecordVO> listMoney(long userId, Pagination pagination, int year, int month);

    /**
     * 添加账单记录
     *
     * @param moneyRecordVO 数据表单
     * @return 是否完成
     */
     List<MoneyRecordVO> addMoney(long userId, MoneyRecordVO moneyRecordVO);

    /**
     * 修改账单记录
     *
     * @param moneyRecordVO 数据表单
     * @return 是否完成
     */
     MoneyRecordVO changeMoney(long userId, MoneyRecordVO moneyRecordVO);

    /**
     * 删除账单记录
     *
     * @param moneyIds 需要删除的账单id
     * @return 是否完成
     */
     List<MoneyRecordVO> deleteMoney(long userId, List<Long> moneyIds, int year, int month);

    /**
     * 账单求和
     * @param userId 用户Id
     * @param year 年份
     * @param month 月份
     * @return 当月支出和、收入和
     */
     MoneySumResponse sum(long userId, int year, int month);
}