package org.money.model.po;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Tag {
    /**
     * 标签id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

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