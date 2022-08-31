package org.money.model.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author zhangyiheng03
 * @since 2022/8/31 9:57
 */
@Data
public class AnalyseLineResponse {

    List<String> name;

    List<BigDecimal> value;

    public AnalyseLineResponse() {
        this.name = Lists.newArrayList();
        this.value = Lists.newArrayList();
    }
}