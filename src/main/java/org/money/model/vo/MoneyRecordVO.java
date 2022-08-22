package org.money.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import org.money.model.po.MoneyRecordPO;
import org.money.util.DateTimeUtils;
import org.springframework.beans.BeanUtils;

/**
 * @author zhangyiheng03
 * @since 2022/8/15 14:32
 */
@Data
@Accessors(chain = true)
public class MoneyRecordVO {
    /**
     * 账单id
     */
    private Long id;

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空")
    private String title;

    /**
     * 金额
     */
    @NotNull(message = "金额不能为空")
    private BigDecimal amount;

    /**
     * true-收入 false-支出
     */
    private Boolean reverse;

    /**
     * 类目id
     */
    private Long categoryId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 支付时间
     */
    private String payTime;

    public static MoneyRecordVO of(MoneyRecordPO moneyRecordPO) {
        MoneyRecordVO res = new MoneyRecordVO();
        BeanUtils.copyProperties(moneyRecordPO, res);
        res.setAmount(BigDecimal.valueOf(moneyRecordPO.getAmount()).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP));
        res.setReverse(Objects.equals(moneyRecordPO.getReverse(), 1) ? Boolean.TRUE : Boolean.FALSE);
        res.setPayTime(DateTimeUtils.parseLongToString(moneyRecordPO.getPayTime(), DateTimeUtils.YEAR_MONTH_DAY_FORMAT));
        return res;
    }
}