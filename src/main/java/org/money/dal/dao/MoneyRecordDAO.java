package org.money.dal.dao;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

import java.util.List;
import java.util.Objects;

import org.money.dal.mapper.MoneyRecordMapper;
import org.money.integration.constant.RedisKeys;
import org.money.model.po.MoneyRecordPO;
import org.money.util.DateTimeUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson2.JSON;

/**
 * @author zhangyiheng03
 * @since 2022/8/16 14:04
 */
@Repository
@Slf4j
public class MoneyRecordDAO {

    @Resource
    MoneyRecordMapper moneyRecordMapper;
    @Resource
    RedisTemplate<String, String> redisTemplate;

    public List<MoneyRecordPO> listByTime(long userId, int year, int month) {
        String redisKey = RedisKeys.genUserMoneyListKey(userId, year, month);
        long startTime = DateTimeUtils.getStartTimeOfMonth(year, month);
        long endTime = DateTimeUtils.getEndTimeOfMonth(year, month);
        if (Objects.equals(redisTemplate.hasKey(redisKey), Boolean.TRUE)) {
            return JSON.parseArray(redisTemplate.opsForValue().get(redisKey), MoneyRecordPO.class);
        }
        List<MoneyRecordPO> allRecord = moneyRecordMapper.selectByTime(userId, startTime, endTime);
        redisTemplate.opsForValue().set(redisKey, JSON.toJSONString(allRecord));
        log.info("[op:listByTime] no cache ,redisKey:{}, allRecord:{}, startTime:{}, endTime:{}",
                redisKey, JSON.toJSONString(allRecord), startTime, endTime);
        return allRecord;
    }

    public boolean add(MoneyRecordPO moneyRecordPO) {
        moneyRecordPO.setCreateTime(System.currentTimeMillis());
        moneyRecordPO.setUpdateTime(System.currentTimeMillis());
        String redisKey = getRedisKey(moneyRecordPO.getPayTime(), moneyRecordPO.getUserId());
        if (Objects.equals(redisTemplate.hasKey(redisKey), Boolean.TRUE)) {
            List<MoneyRecordPO> allRecord = JSON.parseArray(redisTemplate.opsForValue().get(redisKey), MoneyRecordPO.class);
            allRecord.add(moneyRecordPO);
            allRecord.sort((a,b)-> (int) (a.getPayTime() - b.getPayTime()));
            redisTemplate.opsForValue().set(redisKey, JSON.toJSONString(allRecord));
        }
        return moneyRecordMapper.insert(moneyRecordPO) > 0;
    }

    public boolean change(MoneyRecordPO moneyRecordPO) {
        String redisKey = getRedisKey(moneyRecordPO.getPayTime(), moneyRecordPO.getUserId());
        if (Objects.equals(redisTemplate.hasKey(redisKey), Boolean.TRUE)) {
            List<MoneyRecordPO> allRecord = JSON.parseArray(redisTemplate.opsForValue().get(redisKey), MoneyRecordPO.class);
            for (int i = 0; i < allRecord.size(); i++) {
                if (Objects.equals(moneyRecordPO.getId(), allRecord.get(i).getId())) {
                    allRecord.set(i, moneyRecordPO);
                }
            }
            redisTemplate.opsForValue().set(redisKey, JSON.toJSONString(allRecord));
        }
        return moneyRecordMapper.updateByPrimaryKey(moneyRecordPO) > 0;
    }

    public boolean delete(List<Long> ids, int year, int month, long userId) {
        String redisKey = RedisKeys.genUserMoneyListKey(userId, year, month);
        if (Objects.equals(redisTemplate.hasKey(redisKey), Boolean.TRUE)) {
            redisTemplate.delete(redisKey);
        }
        return moneyRecordMapper.deleteBatch(ids);
    }

    private String getRedisKey(long payTime, long userId) {
        int year = DateTimeUtils.getYearFromLong(payTime);
        int month = DateTimeUtils.getMonthFromLong(payTime);
        return RedisKeys.genUserMoneyListKey(userId, year, month);
    }

}