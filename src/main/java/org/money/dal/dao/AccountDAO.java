package org.money.dal.dao;

import javax.annotation.Resource;

import org.money.dal.mapper.AccountMapper;
import org.money.model.po.AccountPO;
import org.springframework.stereotype.Repository;

/**
 * @author zhangyiheng03
 * @since 2022/6/27 10:17
 */
@Repository
public class AccountDAO {
    @Resource
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