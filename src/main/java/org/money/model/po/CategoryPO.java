package org.money.model.po;

import lombok.Data;
import lombok.experimental.Accessors;

import org.money.model.vo.CategoryVO;
import org.springframework.beans.BeanUtils;

@Data
@Accessors(chain = true)
public class CategoryPO {
    /**
     * 类目id
     */
    private Long id;

    /**
     * 描述
     */
    private String name;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;

    public static CategoryPO of(CategoryVO CategoryVO) {
        CategoryPO res = new CategoryPO();
        BeanUtils.copyProperties(CategoryVO, res);
        return res;
    }
}