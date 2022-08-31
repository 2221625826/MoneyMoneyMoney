package org.money.dal.mapper;

import java.util.List;
import org.money.model.po.CategoryPO;

public interface CategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CategoryPO record);

    CategoryPO selectByPrimaryKey(Long id);

    List<CategoryPO> selectAll();

    int updateByPrimaryKey(CategoryPO record);
}