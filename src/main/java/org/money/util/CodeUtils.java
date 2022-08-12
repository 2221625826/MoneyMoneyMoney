package org.money.util;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.util.DigestUtils;

/**
 * @author zhangyiheng03
 * @since 2022/7/4 9:48
 */
@Slf4j
public class CodeUtils {

    private static final Base64.Encoder encoder = Base64.getEncoder();

    private static final Base64.Decoder decoder = Base64.getDecoder();

    public static String MD5Encode(String src) {
        return DigestUtils.md5DigestAsHex(src.getBytes(StandardCharsets.UTF_8));
    }

    public static String Base64Encode(String src) {
        return encoder.encodeToString(src.getBytes(StandardCharsets.UTF_8));
    }

    public static String Base64Decode(String src) {
        return new String(decoder.decode(src), StandardCharsets.UTF_8);
    }
}