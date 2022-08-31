package org.money.model.common;

import lombok.Data;

import java.util.List;
import java.util.Objects;

import com.google.common.collect.Lists;

/**
 * 分页结果
 * @author zhangyiheng03
 * @since 2022/8/15 14:24
 */
@Data
public class PageResult<T> {
    private Pagination pagination;
    private List<T> result;

    public PageResult() {
        this.pagination = new Pagination(0, 0, 0);
        this.result = Lists.newArrayList();
    }

    public PageResult(List<T> result, int page, int pageSize) {
        this.pagination = new Pagination(page, pageSize, 0);
        if (Objects.isNull(result)) {
            this.result = Lists.newArrayList();
        } else {
            this.result = result;
        }
    }

    public PageResult(List<T> result, int page, int pageSize, int total) {
        this.pagination = new Pagination(page, pageSize, total);
        if (Objects.isNull(result)) {
            this.result = Lists.newArrayList();
        } else {
            this.result = result;
        }
    }
}