package org.money.web.configurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.money.web.interceptor.AuthenticationInterceptor;


/**
 * @author zhangyiheng03
 * @since 2022/6/14 17:05
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //加载登录适配器
        registry.addInterceptor(new AuthenticationInterceptor())
                .addPathPatterns("/MoneyMoneyMoney/**")
                .excludePathPatterns("/login");
    }
}
