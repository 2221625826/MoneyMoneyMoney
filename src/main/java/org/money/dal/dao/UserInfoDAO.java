package org.money.dal.dao;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

import org.money.dal.mapper.UserInfoMapper;
import org.money.model.po.UserInfoPO;
import org.springframework.stereotype.Repository;

/**
 * @author zhangyiheng03
 * @since 2022/8/18 16:59
 */
@Repository
@Slf4j
public class UserInfoDAO {
    @Resource
    UserInfoMapper userInfoMapper;

    public boolean edit(UserInfoPO userInfoPO) {
        userInfoPO.setUpdateTime(System.currentTimeMillis());
        return userInfoMapper.updateByPrimaryKey(userInfoPO) > 0;
    }

    public UserInfoPO get(Long userId) {
        return userInfoMapper.getByUserId(userId);
    }

    public boolean insert(UserInfoPO userInfoPO) {
        userInfoPO.setCreateTime(System.currentTimeMillis());
        userInfoPO.setUpdateTime(System.currentTimeMillis());
        return userInfoMapper.insert(userInfoPO) > 0;
    }

}