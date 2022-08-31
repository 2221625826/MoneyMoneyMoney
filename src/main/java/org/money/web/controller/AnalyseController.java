package org.money.web.controller;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

import java.util.Objects;

import org.money.service.AnalyseService;
import org.money.util.http.AjaxResult;
import org.money.web.UserContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangyiheng03
 * @since 2022/8/31 9:51
 */
@RestController
@Slf4j
@RequestMapping("/analyse")
public class AnalyseController extends BaseController{

    @Resource
    AnalyseService analyseService;

    @GetMapping("/pie")
    public AjaxResult getPie(@RequestParam Integer year, @RequestParam(required = false) Integer month) {
        Long userId = UserContext.getUserId();
        if (Objects.isNull(userId)) {
            return initFailureResult("未登录");
        }
        if (Objects.nonNull(month)) {
            return initSuccessResult(analyseService.getPie(userId, year, month));
        } else {
            return initSuccessResult(analyseService.getPie(userId, year));
        }
    }

    @GetMapping("/line")
    public AjaxResult getLine(@RequestParam Integer year, @RequestParam(required = false) Integer month) {
        Long userId = UserContext.getUserId();
        if (Objects.isNull(userId)) {
            return initFailureResult("未登录");
        }
        if (Objects.nonNull(month)) {
            return initSuccessResult(analyseService.getLine(userId, year, month));
        } else {
            return initSuccessResult(analyseService.getLine(userId, year));
        }
    }
}