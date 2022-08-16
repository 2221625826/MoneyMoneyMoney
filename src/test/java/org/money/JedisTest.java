package org.money;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author zhangyiheng03
 * @since 2022/8/16 11:18
 */
@SpringBootTest
public class JedisTest {
    @Resource
    private RedisTemplate<String, String> strRedisTemplate;

    @Test
    public void testString() {
        strRedisTemplate.opsForValue().set("222", "111");
        System.out.println(strRedisTemplate.opsForValue().get("222"));
    }

}