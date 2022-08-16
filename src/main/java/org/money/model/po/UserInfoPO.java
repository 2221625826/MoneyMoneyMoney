package org.money.model.po;

import lombok.Data;

@Data
public class UserInfoPO {
    private Long id;

    private Long userId;

    private Integer sex;

    private String birthday;

    private String introduce;

    private Long createTime;

    private Long updateTime;
}