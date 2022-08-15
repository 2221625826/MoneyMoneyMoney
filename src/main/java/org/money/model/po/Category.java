package org.money.model.po;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Category {
    /**
     * 类目id
     */
    private Long id;

    /**
     * 描述
     */
    private String desc;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;
}