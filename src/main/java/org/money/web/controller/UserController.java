package org.money.web.controller;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.money.service.LoginService;
import org.money.util.exception.ServiceException;
import org.money.util.http.AjaxResult;

/**
 * 登录相关
 * @author zhangyiheng03
 * @since 2022/6/27 15:17
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController extends BaseController{

    @Resource
    LoginService loginService;

    @GetMapping("/login")
    public AjaxResult login(@RequestParam String username, @RequestParam String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return initFailureResult("账号或密码为空");
        }
        log.error("[op:login]: username={}", username);
        String token = loginService.login(username, password);
        if (StringUtils.isBlank(token)) {
            return initFailureResult("账号或密码错误");
        }
        return initSuccessResult(token);
    }

    @GetMapping("/register")
    public AjaxResult register(@RequestParam String username, @RequestParam String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return initFailureResult("账号或密码为空");
        }
        try {
            String token = loginService.register(username, password);
            return initSuccessResult(token);
        } catch (ServiceException se) {
            return initFailureResult(se.getMessage());
        }
    }
}