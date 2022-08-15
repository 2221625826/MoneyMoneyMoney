package org.money.web.controller;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

import org.money.model.po.request.MoneyListRequest;
import org.money.model.vo.MoneyRecordVO;
import org.money.util.http.AjaxResult;
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

    /**
     * 按时间列出账单
     * @param moneyListRequest 请求参数
     * @return 账单列表
     */
    @PostMapping("/list")
    public AjaxResult list(@RequestBody MoneyListRequest moneyListRequest) {
        return initSuccessResult();
    }

    @PostMapping("/add")
    public AjaxResult add(@RequestBody MoneyRecordVO moneyRecordVO) {
        return initSuccessResult();
    }

    @PostMapping("/change")
    public AjaxResult change(@RequestBody MoneyRecordVO moneyRecordVO) {
        if (Objects.isNull(moneyRecordVO.getId())) {
            return initFailureResult("请指定账单记录");
        }
        return initSuccessResult();
    }

    @GetMapping("/batchDelete")
    public AjaxResult change(@RequestParam List<Long> moneyIds) {
        return initSuccessResult();
    }


}