package org.money.model.vo;

import lombok.Data;

import java.util.Objects;

import org.money.model.po.UserInfoPO;
import org.money.util.DateTimeUtils;
import org.springframework.beans.BeanUtils;

/**
 * @author zhangyiheng03
 * @since 2022/8/18 15:34
 */
@Data
public class UserInfoVO {

    /**
     * 用户账号
     */
    private Long userId;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 简介
     */
    private String introduce;

    public static UserInfoVO of(UserInfoPO userInfoPO) {
        UserInfoVO res = new UserInfoVO();
        BeanUtils.copyProperties(userInfoPO, res);
        res.setSex(switch (userInfoPO.getSex()) {
            case 0 -> "女";
            case 1 -> "男";
            default -> "未知";
        });
        if (Objects.isNull(userInfoPO.getBirthday())) {
            res.setBirthday("未填写");
        } else {
            res.setBirthday(DateTimeUtils.parseLongToString(userInfoPO.getBirthday(), DateTimeUtils.YEAR_MONTH_DAY_FORMAT));
        }
        return res;
    }
}