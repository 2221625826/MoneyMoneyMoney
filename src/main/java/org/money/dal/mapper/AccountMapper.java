package org.money.dal.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.money.model.po.AccountPO;

@Mapper
public interface AccountMapper {
    AccountPO selectByUsername(String username);

    int insertSelective(AccountPO accountPO);
}