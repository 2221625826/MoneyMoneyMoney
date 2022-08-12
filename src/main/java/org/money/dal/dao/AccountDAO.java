package org.money.dal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.money.dal.mapper.AccountMapper;
import org.money.model.po.AccountPO;

/**
 * @author zhangyiheng03
 * @since 2022/6/27 10:17
 */
@Repository
public class AccountDAO {
    @Autowired
    AccountMapper accountMapper;

    public AccountPO getAccount(String username) {
        return accountMapper.selectByUsername(username);
    }

    public boolean insert(AccountPO accountPO) {
        accountPO.setCreateTime(System.currentTimeMillis());
        accountPO.setUpdateTime(System.currentTimeMillis());
        return accountMapper.insertSelective(accountPO) == 0;
    }
}