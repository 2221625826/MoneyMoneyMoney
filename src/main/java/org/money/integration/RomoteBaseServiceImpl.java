package org.money.integration;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

import java.io.IOException;
import java.util.Map;

import org.money.util.http.HttpClientTemplate;

/**
 * @author zhangyiheng03
 * @since 2022/5/19 15:54
 */

@Slf4j
public abstract class RomoteBaseServiceImpl {

    @Resource
    protected HttpClientTemplate httpClientTemplate;

    protected static final int GET = 0;
    protected static final int POST = 1;

    /**
     * 加上前缀host
     * @param subUrl host
     * @return 全url
     */
    protected abstract String getFullUrl(String subUrl);

    /**
     * 获取响应结果
     */
    protected String getResponse(String subUrl, Integer type) {
        String fullUrl = getFullUrl(subUrl);
        try {
            return switch (type) {
                case GET -> httpClientTemplate.executeGet(fullUrl);
                case POST -> httpClientTemplate.executePost(fullUrl);
                default -> null;
            };
        } catch (IOException e) {
            log.error("[rpc:{}] failed e={}", fullUrl, e.getMessage());
        }
        return null;
    }
    protected String getResponse(String subUrl, Map<String, String> params, Integer type) {
        String fullUrl = getFullUrl(subUrl);
        try {
            return switch (type) {
                case GET -> httpClientTemplate.executeGet(fullUrl, httpClientTemplate.parseToNameValuePairs(params));
                case POST -> httpClientTemplate.executePost(fullUrl, httpClientTemplate.parseToNameValuePairs(params));
                default -> null;
            };
        } catch (IOException e) {
            log.error("[rpc:{}] failed e={}", fullUrl, e.getMessage());
        }
        return null;
    }
}