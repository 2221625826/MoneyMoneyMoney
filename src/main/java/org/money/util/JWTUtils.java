package org.money.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangyiheng03
 * @since 2022/6/24 14:52
 */
@Slf4j
public class JWTUtils {

    private static final String KEY =
            "ruE+gogsm4V9Tpo+C8upyVfoHpVvTBdZzBQgTEg0uvWuWk16mxCMGG7T4hjOgVj1" +
            "k7trs7Rl153yQPQt6Lsw2W7+N/gGBCPOqCoWFsqtQD9D4ir9icUuF0q5HXVDQq8g" +
            "rK7EZBgprxA880f80/qmR9KtEpOf5jZYmhPRpYOfZb/WXgVNJWTgDcmYD6T77fbj" +
            "HY/J0zgR7NeVSI5OLrxo5ozgPMC2/0olL91T0S73jwzal+nFRHnN9tJ9JCciLPZP" +
            "LjzCvDo14TyfVR8CsDnIjf3lMzREMXHiz4OU5jPLPVLUjjeP/SirsaIeWWbHwfd2" +
            "LiZJXZNCwb0dIuLPQv0WpJGks8fZHE0cvuA75M5N0AhbUni3NdYnxdqildCnMlMB" +
            "g3ljgJXa0GJKMG9q0z3Hd2dDDtcleVNfz7EpsDcfp+/VbXO20HlvC6kmCulkeQLw" +
            "2jDVF37ZhaHPpd1FmNEY6hmT/bvtFAj/XthGnsDmHzpd7XAfq95CC65fOJ0HatnA" +
            "ws3V6ghc6WxXFfDxWLuUi8ieMdPYC8+KnED+7I7rD+CHIgvle1N/CN0OstyYk6m7" +
            "BAycJFGsczRsi5vlV5N3aW2mQlBFwECMXzl5xs6bnbKY6b9XgxOfC+tFbnhKHWN9" +
            "UaRHLRZesH3p+IGKnemToi4+mIK7BBuQQXViF0/fW5ea51h3IsSmFrvaZdk8kktx" +
            "byASQWfrn9eX7tsf86VgejBmmJ7bvDskTD1TDd5IKxPR7jB22+MxLpZLvEFduSH7" +
            "8vr2vYeolo7zYAz+syH5RC0KPUPdSAaUlCrvqYzCUcGSRAC7XgVZb6zCD8L9oVKy" +
            "TiRX7rz61pYpLpecO+HnJE9Dw+bGz5qPsorMMw7+ArNeqxhm+H69l025v3xsLUJH";

    private static final long USEFUL_TIME = DateTimeUtils.TIME_OF_DAY;

    /**
     * 创建token
     * @param subject 主题
     * @return token
     */
    public static String createToken(String subject) {
        try{
            return Jwts.builder()
                    .setSubject(subject)
                    .setExpiration(new Date(System.currentTimeMillis() + USEFUL_TIME))
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .signWith(SignatureAlgorithm.HS512, KEY.getBytes(StandardCharsets.UTF_8))
                    .compact();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }

    }

    /**
     * 解析token
     * @param token token字符串
     * @return 用户id
     */
    public static Long getUserIdFromToken(String token)
    {
        Claims body;
        try {
            body = Jwts.parser().setSigningKey(KEY.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token).getBody();
            return Long.valueOf(body.getSubject());
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * 校验token
     * @param token token字符串
     * @return token是否合法
     */
    public static boolean checkToken(String token) {
        if(StringUtils.isBlank(token)) return false;
        try {
            Jwts.parser().setSigningKey(KEY.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}