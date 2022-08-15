package org.money.dal.mapper;

import java.util.List;
import org.money.model.po.MoneyRecord;

public interface MoneyRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MoneyRecord record);

    MoneyRecord selectByPrimaryKey(Long id);

    List<MoneyRecord> selectAll();

    int updateByPrimaryKey(MoneyRecord record);
}