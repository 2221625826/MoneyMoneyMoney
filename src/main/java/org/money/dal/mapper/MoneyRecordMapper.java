package org.money.dal.mapper;

import java.util.List;
import org.money.model.po.MoneyRecordPO;

public interface MoneyRecordMapper {

    int insert(MoneyRecordPO record);

    List<MoneyRecordPO> selectByTime(long userId, long startTime, long endTime);

    int updateByPrimaryKey(MoneyRecordPO record);

    boolean deleteBatch(List<Long> ids);
}