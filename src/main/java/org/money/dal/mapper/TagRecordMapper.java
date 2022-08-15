package org.money.dal.mapper;

import java.util.List;
import org.money.model.po.TagRecord;

public interface TagRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TagRecord record);

    TagRecord selectByPrimaryKey(Long id);

    List<TagRecord> selectAll();

    int updateByPrimaryKey(TagRecord record);
}