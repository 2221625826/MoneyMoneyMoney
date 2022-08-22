package org.money.model.po;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.money.model.vo.MoneyRecordVO;
import org.money.util.DateTimeUtils;
import org.springframework.beans.BeanUtils;

@Data
@Accessors(chain = true)
@Slf4j
public class MoneyRecordPO {
    /**
     * 账单id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 标题
     */
    private String title;

    /**
     * 金额
     */
    private Long amount;

    /**
     * 0-收入 1-支出
     */
    private Integer reverse;

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
    private Long payTime;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 修改时间
     */
    private Long updateTime;

    public static MoneyRecordPO of(MoneyRecordVO moneyRecordVO) {
        MoneyRecordPO res = new MoneyRecordPO();
        BeanUtils.copyProperties(moneyRecordVO, res);
        res.setAmount(moneyRecordVO.getAmount().multiply(BigDecimal.valueOf(100)).longValue());
        res.setReverse(Objects.equals(moneyRecordVO.getReverse(), Boolean.TRUE) ? 1 : 0);
        try {
            if (StringUtils.isBlank(moneyRecordVO.getPayTime())) {
                res.setPayTime(System.currentTimeMillis());
            } else {
                res.setPayTime(DateTimeUtils.parseStringToLong(moneyRecordVO.getPayTime(), DateTimeUtils.YEAR_MONTH_DAY_FORMAT));
            }
        } catch (Exception ex) {
            log.error("[op:convert] parse payTime fail, payTime:{}", moneyRecordVO.getPayTime());
        }
        return res;
    }
}