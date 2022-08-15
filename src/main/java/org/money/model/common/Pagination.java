package org.money.model.common;

import lombok.Data;

/**
 * 分页参数
 * @author zhangyiheng03
 * @since 2022/8/15 14:22
 */
@Data
public class Pagination {

    private int page;
    private int size;
    private int totalPage;
    private int total;

    public Pagination(int page, int size, int total) {
        this.page = page;
        this.size = size;
        this.total = total;
        if (size > 0) {
            this.totalPage = 1 + (total - 1) / size;
        } else {
            this.totalPage = 1;
        }
    }

    public Pagination(int page, int size) {
        this.page = page;
        this.size = size;
    }
}