package org.money.service;

import java.util.List;

import org.money.model.vo.CategoryVO;

/**
 * @author zhangyiheng03
 * @since 2022/8/30 16:48
 */

public interface CategoryService {

    List<CategoryVO> getCategories();
}