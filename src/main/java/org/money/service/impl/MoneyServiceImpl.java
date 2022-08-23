package org.money.service.impl;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.money.dal.dao.MoneyRecordDAO;
import org.money.model.common.PageResult;
import org.money.model.common.Pagination;
import org.money.model.po.MoneyRecordPO;
import org.money.model.response.MoneySumResponse;
import org.money.model.vo.MoneyRecordVO;
import org.money.service.MoneyService;
import org.money.util.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = {SQLException.class, ServiceException.class})
    public boolean changeMoney(long userId, MoneyRecordVO moneyRecordVO) {
        MoneyRecordPO moneyRecordPO = MoneyRecordPO.of(moneyRecordVO);
        moneyRecordPO.setUserId(userId);
        return moneyRecordDAO.change(moneyRecordPO);
    }

    @Override
    public boolean deleteMoney(long userId, List<Long> moneyIds, int year, int month) {
        List<MoneyRecordPO> allRecord = moneyRecordDAO.listByTime(userId, year, month);
        if (moneyIds.retainAll(allRecord.stream().map(MoneyRecordPO::getId).collect(Collectors.toList()))) {
            log.error("[op:deleteMoney] illegal parameter userId:{}, year:{}, month:{}, moneyIds:{}",
                    userId, year, month, moneyIds);
            throw new ServiceException("试图删除 不在当月的记录");
        }
        return moneyRecordDAO.delete(moneyIds, year, month, userId);
    }

    @Override
    public MoneySumResponse sum(long userId, int year, int month) {
        List<MoneyRecordPO> allRecord = moneyRecordDAO.listByTime(userId, year, month);
        long in = allRecord.stream().filter(r -> Objects.equals(r.getReverse(), 0)).map(MoneyRecordPO::getAmount).reduce(0L, Long::sum);
        long out = allRecord.stream().filter(r -> Objects.equals(r.getReverse(), 1)).map(MoneyRecordPO::getAmount).reduce(0L, Long::sum);
        MoneySumResponse ret = new MoneySumResponse();
        ret.setIn(BigDecimal.valueOf(in).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP));
        ret.setOut(BigDecimal.valueOf(out).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP));
        return ret;
    }
}