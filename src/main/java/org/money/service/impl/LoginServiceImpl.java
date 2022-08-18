package org.money.service.impl;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

import java.sql.SQLException;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.money.dal.dao.UserInfoDAO;
import org.money.model.po.UserInfoPO;
import org.money.model.vo.UserInfoVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.money.dal.dao.AccountDAO;
import org.money.model.po.AccountPO;
import org.money.service.LoginService;
import org.money.util.CodeUtils;
import org.money.util.JWTUtils;
import org.money.util.exception.ServiceException;


/**
 * @author zhangyiheng03
 * @since 2022/6/27 15:14
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    @Resource
    AccountDAO accountDAO;

    @Resource
    UserInfoDAO userInfoDAO;

    @Override
    public String login(String username, String password) {
        AccountPO account = accountDAO.getAccount(username);
        password = CodeUtils.Base64Decode(password);
        if (Objects.nonNull(account) && StringUtils.equals(account.getPassword(), password)) {
            return JWTUtils.createToken(String.valueOf(account.getId()));
        } else {
            log.info("[op:login]: login fail username={}, password={}", username, password);
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = {SQLException.class}, noRollbackFor = {ServiceException.class})
    public String register(String username, String password) {
        AccountPO account = accountDAO.getAccount(username);
        if (Objects.nonNull(account)) {
            throw new ServiceException("账号已存在");
        }
        password = CodeUtils.Base64Decode(password);
        if (!passwordValidate(password.toCharArray())) {
            throw new ServiceException("密码不符合要求");
        }
        account = AccountPO.build(username, password);
        accountDAO.insert(account);
        userInfoDAO.insert(UserInfoPO.build(account.getId()));
        log.info("[op:register] userId={}", account.getId());
        return JWTUtils.createToken(String.valueOf(account.getId()));
    }

    @Override
    @Transactional(rollbackFor = {SQLException.class, ServiceException.class})
    public UserInfoVO editUserInfo(UserInfoVO userInfoVO) {
        UserInfoPO userInfoPO = UserInfoPO.of(userInfoVO);
        if (userInfoDAO.edit(userInfoPO)) {
            return userInfoVO;
        } else {
            throw new ServiceException("修改失败");
        }

    }

    @Override
    public UserInfoVO getUserInfo(Long userId) {
        return UserInfoVO.of(userInfoDAO.get(userId));
    }


    private boolean passwordValidate(char[] password) {
        int length = password.length;
        //长度8-30位
        if (length < 8 || length > 30) {
            return false;
        }
        //只有且必须有大小写字母和数字
        int big = 0, small = 0, num = 0;
        for (char c : password) {
            if (c >= '0' && c <= '9') {
                num++;
            } else if (c >= 'a' && c <= 'z') {
                small++;
            } else if (c >= 'A' && c <= 'Z') {
                big++;
            }
        }
        return big * small * num > 0 && big + small + num == length;
    }
}