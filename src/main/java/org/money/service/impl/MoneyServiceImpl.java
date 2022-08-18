package org.money.service.impl;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import org.money.dal.dao.MoneyRecordDAO;
import org.money.model.common.PageResult;
import org.money.model.common.Pagination;
import org.money.model.po.MoneyRecordPO;
import org.money.model.vo.MoneyRecordVO;
import org.money.service.MoneyService;
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
    public boolean addMoney(long userId, MoneyRecordVO moneyRecordVO) {
        MoneyRecordPO moneyRecordPO = MoneyRecordPO.of(moneyRecordVO);
        moneyRecordPO.setUserId(userId);
        return moneyRecordDAO.add(moneyRecordPO);
    }

    @Override
    public boolean changeMoney(long userId, MoneyRecordVO moneyRecordVO) {
        MoneyRecordPO moneyRecordPO = MoneyRecordPO.of(moneyRecordVO);
        moneyRecordPO.setUserId(userId);
        return moneyRecordDAO.change(moneyRecordPO);
    }

    @Override
    public boolean deleteMoney(long userId, List<Long> moneyIds, int year, int month) {
        return moneyRecordDAO.delete(moneyIds, year, month, userId);
    }
}