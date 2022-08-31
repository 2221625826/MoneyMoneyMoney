package org.money.dal.dao;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

import java.util.List;
import java.util.Objects;

import org.money.dal.mapper.CategoryMapper;
import org.money.integration.constant.RedisKeys;
import org.money.model.po.CategoryPO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;

/**
 * @author zhangyiheng03
 * @since 2022/8/31 10:39
 */
@Repository
@Slf4j
public class CategoryDAO {
    @Resource
    CategoryMapper categoryMapper;
    @Resource
    RedisTemplate<String, String> redisTemplate;

    public List<CategoryPO> getAllCategories() {
        if (Objects.equals(redisTemplate.hasKey(RedisKeys.CATEGORY_KEY), Boolean.TRUE)) {
            return JSON.parseArray(redisTemplate.opsForValue().get(RedisKeys.CATEGORY_KEY), CategoryPO.class);
        }
        List<CategoryPO> res = categoryMapper.selectAll();
        redisTemplate.opsForValue().set(RedisKeys.CATEGORY_KEY, JSON.toJSONString(res));
        return res;
    }
}