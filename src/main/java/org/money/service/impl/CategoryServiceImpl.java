package org.money.service.impl;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

import java.util.List;
import java.util.stream.Collectors;

import org.money.dal.dao.CategoryDAO;
import org.money.model.vo.CategoryVO;
import org.money.service.CategoryService;
import org.springframework.stereotype.Service;

/**
 * @author zhangyiheng03
 * @since 2022/8/31 10:25
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Resource
    CategoryDAO categoryDAO;

    @Override
    public List<CategoryVO> getCategories() {
        return categoryDAO.getAllCategories().stream().map(CategoryVO::of).collect(Collectors.toList());
    }
}