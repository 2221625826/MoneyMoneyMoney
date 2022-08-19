package org.money.util.http;

import lombok.AllArgsConstructor;

import org.money.model.enums.HttpCode;

/**
 * @author zhangyiheng03
 * @since 2022/5/18 14:51
 */

@AllArgsConstructor
public class AjaxResult {
    /**
     * HTTP响应码
     */
    public Integer code;

    /**
     * 消息
     */
    public String msg;

    /**
     * 结果
     */
    public Object data;

    public AjaxResult(HttpCode code, String msg, Object result) {
        this.code = code.getCode();
        this.msg = msg;
        this.data = result;
    }

    public AjaxResult(HttpCode code, String msg) {
        this.code = code.getCode();
        this.msg = msg;
    }

    public AjaxResult(HttpCode code, Object result) {
        this.code = code.getCode();
        this.msg = code.getMsg();
        this.data = result;
    }

    public AjaxResult(HttpCode code) {
        this.code = code.getCode();
        this.msg = code.getMsg();
    }
}