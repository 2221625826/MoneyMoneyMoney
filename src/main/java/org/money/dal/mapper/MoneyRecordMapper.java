package org.money.dal.mapper;

import java.util.List;
import org.money.model.po.MoneyRecordPO;

public interface MoneyRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MoneyRecordPO record);

    MoneyRecordPO selectByPrimaryKey(Long id);

    List<MoneyRecordPO> selectAll();

    int updateByPrimaryKey(MoneyRecordPO record);
}