package org.money.model.request;

import lombok.Getter;

import java.util.List;

/**
 * @author zhangyiheng03
 * @since 2022/8/16 17:12
 */
@Getter
public class MoneyDeleteRequest {
    /**
     * 删除的年
     */
    int year;
    /**
     * 删除的月
     */
    int month;
    /**
     * 需要删除的ids
     */
    List<Long> ids;
}