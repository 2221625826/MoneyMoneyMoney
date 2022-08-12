package org.money.util.exception;

/**
 * 服务异常类
 * @author zhangyiheng03
 * @since 2022/5/18 15:25
 */

public class ServiceException extends RuntimeException{
    public ServiceException(String msg) {
        super(msg);
    }
}