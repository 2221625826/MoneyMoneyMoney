package org.money.util.http;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.common.collect.Lists;

/**
 * Http工具类
 * @author zhangyiheng03
 * @since 2022/5/19 11:33
 */

public class HttpClientTemplate {

    private final CloseableHttpClient httpClient = HttpClients.custom().build();

    private static final int TIME_OUT = 50000;

    private static final String DEFAULT_CHARSET = "utf-8";

    /**
     * 执行post
     */
    public String executePost(String url) throws IOException {
        return executePost(url, null);
    }
    public String executePost(String url, List<NameValuePair> params) throws IOException {
        return executePost(url, params, DEFAULT_CHARSET);
    }
    public String executePost(String url, List<NameValuePair> params, int timeout) throws IOException {
        return executePost(url, params, DEFAULT_CHARSET, timeout);
    }
    public String executePost(String url, List<NameValuePair> params, String charset) throws IOException {
        return executePost(url, params, charset, TIME_OUT);
    }
    public String executePost(String url, List<NameValuePair> params, String charset, int timeout)
            throws IOException {
        charset = charset != null ? charset : DEFAULT_CHARSET;
        HttpPost postRequest = getHttpPost(url, timeout);
        if (Objects.nonNull(params) && !params.isEmpty()) {
            setPostParams(postRequest, params, charset);
        }
        return requestAndParse(postRequest, charset);
    }

    /**
     * 执行get
     */
    public String executeGet(String url) throws IOException {
        return executeGet(url, null);
    }
    public String executeGet(String url, List<NameValuePair> params) throws IOException {
        return executeGet(url, params, DEFAULT_CHARSET, TIME_OUT);
    }
    public String executeGet(String url, List<NameValuePair> params, int timeout) throws IOException {
        return executeGet(url, params, DEFAULT_CHARSET, timeout);
    }
    public String executeGet(String url, List<NameValuePair> params, String charset) throws IOException {
        return executeGet(url, params, charset, TIME_OUT);
    }
    public String executeGet(String url, List<NameValuePair> params, String charset, int timeout)
            throws IOException {
        charset = charset != null ? charset : DEFAULT_CHARSET;
        HttpGet getRequest = buildGetRequest(url, params, charset, timeout);
        return requestAndParse(getRequest, charset);
    }

    /**
     * 设置post参数
     */
    private void setPostParams(HttpPost post, List<NameValuePair> params, String charset)
            throws IOException {
        charset = charset != null ? charset : DEFAULT_CHARSET;
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(params, charset);
        post.setEntity(urlEncodedFormEntity);
    }

    /**
     * 获取带参get
     */
    private HttpGet buildGetRequest(String requestUrl, List<NameValuePair> params, String charset, int timeout) {
        String queryString = null;
        if (Objects.nonNull(params) && !params.isEmpty()) {
            charset = charset != null ? charset : DEFAULT_CHARSET;
            queryString = URLEncodedUtils.format(params, charset);
        }
        if (queryString != null) {
            if (!requestUrl.contains("?")) {
                requestUrl += "?" + queryString;
            } else {
                requestUrl += "&" + queryString;
            }
        }
        return getHttpGet(requestUrl, timeout);
    }

    /**
     * 创造请求
     */
    private HttpPost getHttpPost(String url, int timeout) {
        timeout = timeout <= 0 ? TIME_OUT : timeout;
        HttpPost postMethod = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout).setSocketTimeout(timeout).setRedirectsEnabled(false).build();
        postMethod.setConfig(requestConfig);
        return postMethod;
    }
    private HttpGet getHttpGet(String url, int timeout) {
        timeout = timeout <= 0 ? TIME_OUT : timeout;
        HttpGet getMethod = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout).setSocketTimeout(timeout).setRedirectsEnabled(false).build();
        getMethod.setConfig(requestConfig);
        return getMethod;
    }

    /**
     * 请求并返回结果
     */
    private String requestAndParse(HttpUriRequest httpRequest, String charset) throws IOException {
        HttpResponse httpResponse = httpClient.execute(httpRequest);
        return getResponseContentStr(httpResponse, charset);
    }

    /**
     * 获取响应中的字符串
     */
    private String getResponseContentStr(HttpResponse httpResponse, String charset) throws IOException {
        HttpEntity entity = getResponseContentEntity(httpResponse);
        if (Objects.isNull(entity)) {
            return null;
        }
        return EntityUtils.toString(entity, charset);
    }
    /**
     * 获取响应中的结果
     */
    private HttpEntity getResponseContentEntity(HttpResponse httpResponse) throws IOException {
        StatusLine statusLine = httpResponse.getStatusLine();
        if (null == statusLine) {
            EntityUtils.consumeQuietly(httpResponse.getEntity());
            throw new IOException("status not specified");
        }
        int statusCode = statusLine.getStatusCode();
        if (statusCode < 200 || statusCode > 299) {
            EntityUtils.consumeQuietly(httpResponse.getEntity());
            throw new IOException("status code: " + statusCode);
        }
        return httpResponse.getEntity();
    }

    /**
     * 参数转换
     * @param map 参数map
     * @return 参数对列表
     */
    public List<NameValuePair> parseToNameValuePairs(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return Lists.newArrayList();
        }

        List<NameValuePair> formParams = Lists.newArrayList();
        for (Map.Entry<String, String> entry: map.entrySet()) {
            formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return formParams;
    }
}