package org.money.dal.mapper;

import java.util.List;
import org.money.model.po.PayEarnRecord;

public interface PayEarnRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PayEarnRecord record);

    PayEarnRecord selectByPrimaryKey(Long id);

    List<PayEarnRecord> selectAll();

    int updateByPrimaryKey(PayEarnRecord record);
}