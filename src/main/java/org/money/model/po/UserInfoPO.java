package org.money.model.po;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.money.model.vo.UserInfoVO;
import org.money.util.DateTimeUtils;
import org.springframework.beans.BeanUtils;

@Data
@Slf4j
public class UserInfoPO {
    /**
     * 用户信息Id
     */
    private Long id;

    /**
     * 用户账号
     */
    private Long userId;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 性别 0-女 1-男 2-未知
     */
    private Integer sex;

    /**
     * 生日
     */
    private Long birthday;

    /**
     * 简介
     */
    private String introduce;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;

    public static UserInfoPO build(Long userId) {
        UserInfoPO res = new UserInfoPO();
        res.setUserId(userId);
        res.setSex(2);
        res.setBirthday(null);
        res.setIntroduce("");
        res.setUpdateTime(System.currentTimeMillis());
        res.setCreateTime(System.currentTimeMillis());
        return res;
    }

    public static UserInfoPO of(UserInfoVO userInfoVO) {
        UserInfoPO res = new UserInfoPO();
        BeanUtils.copyProperties(userInfoVO, res);
        res.setSex(switch (userInfoVO.getSex()) {
            case "女" -> 0;
            case "男" -> 1;
            default -> 2;
        });
        try {
            res.setBirthday(DateTimeUtils.parseStringToLong(userInfoVO.getBirthday(), DateTimeUtils.YEAR_MONTH_DAY_FORMAT));
        } catch (Exception ex) {
            log.error("[op:convert] parse birthday fail, birthday:{}", userInfoVO.getBirthday());
        }
        return res;
    }
}