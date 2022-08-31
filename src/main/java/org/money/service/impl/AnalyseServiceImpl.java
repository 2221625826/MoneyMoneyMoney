package org.money.service.impl;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.money.dal.dao.MoneyRecordDAO;
import org.money.model.po.MoneyRecordPO;
import org.money.model.response.AnalyseLineResponse;
import org.money.model.response.AnalysePieResponse;
import org.money.model.vo.CategoryVO;
import org.money.service.AnalyseService;
import org.money.service.CategoryService;
import org.money.util.DateTimeUtils;
import org.money.util.MoneyUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @author zhangyiheng03
 * @since 2022/8/31 10:15
 */
@Service
@Slf4j
public class AnalyseServiceImpl implements AnalyseService {

    @Resource
    MoneyRecordDAO moneyRecordDAO;

    @Resource
    CategoryService categoryService;

    @Override
    public List<AnalysePieResponse> getPie(long userId, int year, int month) {
        List<MoneyRecordPO> allRecord = moneyRecordDAO.listByMonth(userId, year, month);
        Map<Long, BigDecimal> categorySum = Maps.newHashMap();
        for (MoneyRecordPO record : allRecord) {
            BigDecimal amount = categorySum.getOrDefault(record.getCategoryId(), MoneyUtils.ZERO)
                    .add(MoneyUtils.longToBigDecimal(record.getAmount()));
            categorySum.put(record.getCategoryId(), amount);
        }
        List<CategoryVO> categories = categoryService.getCategories();
        List<AnalysePieResponse> responses = Lists.newArrayList();
        for (CategoryVO category : categories) {
            if (categorySum.containsKey(category.getId())) {
                responses.add(new AnalysePieResponse(category.getName(), categorySum.get(category.getId())));
            }
        }
        return responses;
    }

    @Override
    public List<AnalysePieResponse> getPie(long userId, int year) {
        return null;
    }

    @Override
    public AnalyseLineResponse getLine(long userId, int year, int month) {
        List<MoneyRecordPO> allRecord = moneyRecordDAO.listByMonth(userId, year, month);
        AnalyseLineResponse response = new AnalyseLineResponse();
        Map<Integer, BigDecimal> map = Maps.newHashMap();
        int days = DateTimeUtils.getDaysOfMonth(year, month);
        for (MoneyRecordPO record : allRecord) {
            int day = DateTimeUtils.getDayFromLong(record.getPayTime());
            BigDecimal amount = map.getOrDefault(day, MoneyUtils.ZERO).add(MoneyUtils.longToBigDecimal(record.getAmount()));
            map.put(day, amount);
        }
        for (int day = 1; day <= days; day++) {
            response.getName().add(String.valueOf(day));
            response.getValue().add(map.getOrDefault(day, MoneyUtils.ZERO));
        }
        return response;
    }

    @Override
    public AnalyseLineResponse getLine(long userId, int year) {
        return null;
    }
}