package org.money.dal.mapper;

import java.util.List;
import org.money.model.po.Category;

public interface CategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Category record);

    Category selectByPrimaryKey(Long id);

    List<Category> selectAll();

    int updateByPrimaryKey(Category record);
}