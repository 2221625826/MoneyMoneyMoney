package org.money.service.impl;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.money.dal.dao.MoneyRecordDAO;
import org.money.model.common.PageResult;
import org.money.model.common.Pagination;
import org.money.model.po.MoneyRecordPO;
import org.money.model.vo.MoneyRecordVO;
import org.money.service.MoneyService;
import org.money.util.DateTimeUtils;
import org.money.util.exception.ServiceException;
import org.springframework.stereotype.Service;

/**
 * @author zhangyiheng03
 * @since 2022/8/16 14:03
 */
@Service
@Slf4j
public class MoneyServiceImpl implements MoneyService {

    @Resource
    MoneyRecordDAO moneyRecordDAO;

    @Override
    public PageResult<MoneyRecordVO> listMoney(long userId, Pagination pagination, int year, int month) {
        List<MoneyRecordPO> allRecord = moneyRecordDAO.listByTime(userId, year, month);
        PageResult<MoneyRecordVO> res = new PageResult<>();
        pagination.setTotal(allRecord.size());
        res.setPagination(pagination);
        List<MoneyRecordVO> retList = new ArrayList<>();
        for (int i = pagination.genStartIndex(); i <= Math.min(pagination.genEndIndex(), allRecord.size() - 1); i++) {
            retList.add(MoneyRecordVO.of(allRecord.get(i)));
        }
        res.setResult(retList);
        return res;
    }

    @Override
    public List<MoneyRecordVO> addMoney(long userId, MoneyRecordVO moneyRecordVO) {
        MoneyRecordPO moneyRecordPO = MoneyRecordPO.of(moneyRecordVO);
        moneyRecordPO.setUserId(userId);
        moneyRecordDAO.add(moneyRecordPO);
        long payTime = moneyRecordPO.getPayTime();
        int year = DateTimeUtils.getYearFromLong(payTime);
        int month = DateTimeUtils.getMonthFromLong(payTime);
        return moneyRecordDAO.listByTime(userId, year, month).stream().map(MoneyRecordVO::of).collect(Collectors.toList());
    }

    @Override
    public MoneyRecordVO changeMoney(long userId, MoneyRecordVO moneyRecordVO) {
        MoneyRecordPO moneyRecordPO = MoneyRecordPO.of(moneyRecordVO);
        moneyRecordPO.setUserId(userId);
        moneyRecordDAO.change(moneyRecordPO);
        return moneyRecordVO;
    }

    @Override
    public List<MoneyRecordVO> deleteMoney(long userId, List<Long> moneyIds, int year, int month) {
        List<MoneyRecordPO> allRecord = moneyRecordDAO.listByTime(userId, year, month);
        if (moneyIds.retainAll(allRecord.stream().map(MoneyRecordPO::getId).collect(Collectors.toList()))) {
            log.error("[op:deleteMoney] illegal parameter userId:{}, year:{}, month:{}, moneyIds:{}",
                    userId, year, month, moneyIds);
            throw new ServiceException("试图删除 不在当月的记录");
        }
        moneyRecordDAO.delete(moneyIds, year, month, userId);
        return moneyRecordDAO.listByTime(userId, year, month).stream().map(MoneyRecordVO::of).collect(Collectors.toList());
    }
}