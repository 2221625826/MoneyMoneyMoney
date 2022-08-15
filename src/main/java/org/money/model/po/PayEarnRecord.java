package org.money.model.po;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PayEarnRecord {
    /**
     * 账单id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 金额
     */
    private Long amount;

    /**
     * 0-支出 1-收入
     */
    private Integer reverse;

    /**
     * 类目id
     */
    private Long categoryId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 支付时间
     */
    private Long payTime;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 修改时间
     */
    private Long updateTime;
}