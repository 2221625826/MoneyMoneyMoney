package org.money;

import org.junit.jupiter.api.Test;
import org.money.model.po.UserInfoPO;
import org.money.model.vo.UserInfoVO;
import org.springframework.boot.test.context.SpringBootTest;

import com.alibaba.fastjson2.JSON;

/**
 * @author zhangyiheng03
 * @since 2022/8/16 11:18
 */
@SpringBootTest
public class BizTest {


    @Test
    public void testOf() {
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setUserId(1L);
        userInfoVO.setSex("女");
        userInfoVO.setBirthday("2022年08-16日");
        UserInfoPO userInfoPO = UserInfoPO.of(userInfoVO);
        System.out.printf(JSON.toJSONString(userInfoPO));
        System.out.printf(JSON.toJSONString(UserInfoVO.of(userInfoPO)));
    }

}