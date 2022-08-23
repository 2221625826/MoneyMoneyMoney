package org.money.web.controller;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

import java.util.Objects;

import org.money.model.common.Pagination;
import org.money.model.request.MoneyDeleteRequest;
import org.money.model.request.MoneyListRequest;
import org.money.model.vo.MoneyRecordVO;
import org.money.service.MoneyService;
import org.money.util.http.AjaxResult;
import org.money.web.UserContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangyiheng03
 * @since 2022/8/12 9:33
 */
@RestController
@Slf4j
@RequestMapping("/money")
public class MoneyController extends BaseController{

    @Resource
    MoneyService moneyService;

    /**
     * 按时间列出账单
     * @param moneyListRequest 请求参数
     * @return 账单列表
     */
    @PostMapping("/list")
    public AjaxResult list(@RequestBody MoneyListRequest moneyListRequest) {
        Long userId = UserContext.getUserId();
        if (Objects.isNull(userId)) {
            return initFailureResult("未登录");
        }
        return initSuccessResult(
                moneyService.listMoney(
                        userId,
                        new Pagination(moneyListRequest.getPage(), moneyListRequest.getPageSize()),
                        moneyListRequest.getYear(),
                        moneyListRequest.getMonth()
                )
        );
    }

    @PostMapping("/add")
    public AjaxResult add(@RequestBody MoneyRecordVO moneyRecordVO) {
        Long userId = UserContext.getUserId();
        if (Objects.isNull(userId)) {
            return initFailureResult("未登录");
        }
        if (Objects.isNull(moneyRecordVO.getId())) {
            return initSuccessResult(moneyService.addMoney(userId, moneyRecordVO));
        } else {
            return initSuccessResult(moneyService.changeMoney(userId, moneyRecordVO));
        }
    }

    @PostMapping("/batchDelete")
    public AjaxResult delete(@RequestBody MoneyDeleteRequest moneyDeleteRequest) {
        Long userId = UserContext.getUserId();
        if (Objects.isNull(userId)) {
            return initFailureResult("未登录");
        }
        return initSuccessResult(
                moneyService.deleteMoney(
                        userId,
                        moneyDeleteRequest.getIds(),
                        moneyDeleteRequest.getYear(),
                        moneyDeleteRequest.getMonth()
                )
        );
    }

    @GetMapping("/sum")
    public AjaxResult sum(@RequestParam int year,@RequestParam int month) {
        Long userId = UserContext.getUserId();
        if (Objects.isNull(userId)) {
            return initFailureResult("未登录");
        }
        return initSuccessResult(moneyService.sum(userId, year, month));
    }
}