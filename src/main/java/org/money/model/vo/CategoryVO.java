package org.money.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import org.money.model.po.CategoryPO;
import org.springframework.beans.BeanUtils;

/**
 * @author zhangyiheng03
 * @since 2022/8/31 10:26
 */
@Data
@Accessors(chain = true)
public class CategoryVO {
    /**
     * 类目id
     */
    private Long id;

    /**
     * 描述
     */
    private String name;

    public static CategoryVO of(CategoryPO CategoryPO) {
        CategoryVO res = new CategoryVO();
        BeanUtils.copyProperties(CategoryPO, res);
        return res;
    }
}