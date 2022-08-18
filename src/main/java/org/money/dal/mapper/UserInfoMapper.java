package org.money.dal.mapper;

import org.money.model.po.UserInfoPO;

public interface UserInfoMapper {
    int updateByPrimaryKey(UserInfoPO record);

    UserInfoPO getByUserId(long userId);

    int insert(UserInfoPO record);
}