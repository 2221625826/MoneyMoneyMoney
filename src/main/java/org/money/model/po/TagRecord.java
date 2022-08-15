package org.money.model.po;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TagRecord {
    /**
     * 标签记录id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 标签id
     */
    private Long tagId;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;
}