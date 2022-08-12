package org.money.web.controller;

import org.money.model.enums.HttpCode;
import org.money.util.exception.ServiceException;
import org.money.util.http.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangyiheng03
 * @since 2022/5/18 14:46
 */
@Slf4j
public class BaseController {
    protected AjaxResult initSuccessResult() {
        return new AjaxResult(HttpCode.SUCCESS);
    }

    protected AjaxResult initSuccessResult(Object data) {
        return new AjaxResult(HttpCode.SUCCESS, data);
    }

    protected AjaxResult initSuccessResult(String  msg, Object data) {
        return new AjaxResult(HttpCode.SUCCESS, msg, data);
    }

    protected AjaxResult initFailureResult(HttpCode code) {
        return new AjaxResult(code);
    }

    protected AjaxResult initFailureResult(String msg) {
        return new AjaxResult(HttpCode.DEFAULT_ERROR, msg);
    }

    protected AjaxResult initFailureResult(HttpCode code, String msg, Object data) {
        return new AjaxResult(HttpCode.DEFAULT_ERROR, msg, data);
    }

    @ExceptionHandler(value = Throwable.class)
    @ResponseBody
    public AjaxResult handleException(HttpServletRequest request, Exception ex) {
        String path = request.getRequestURI();
        if (ex instanceof ServiceException) {
            ServiceException serviceEx = (ServiceException) ex;
            log.warn("[{}] service exception. message={}", path, serviceEx.getMessage());
            return new AjaxResult(HttpCode.NOT_FOUNT, serviceEx.getMessage());
        } else if (ex instanceof IllegalArgumentException) {
            return new AjaxResult(HttpCode.PARAM_ERROR, ex.getMessage());
        } else {
            log.error("[{}] exception. message={}", path, ex.getMessage());
            return new AjaxResult(HttpCode.DEFAULT_ERROR, ex.getMessage());
        }
    }
}