package org.money.dal.mapper;

import java.util.List;
import org.money.model.po.Tag;

public interface TagMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Tag record);

    Tag selectByPrimaryKey(Long id);

    List<Tag> selectAll();

    int updateByPrimaryKey(Tag record);
}