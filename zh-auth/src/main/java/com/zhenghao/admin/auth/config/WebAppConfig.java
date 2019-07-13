package com.zhenghao.admin.auth.config;

import com.zhenghao.admin.auth.interceptor.TokenAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 🙃
 * 🙃 注册拦截器
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/01/13 21:54
 * WebAppConfig.java
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenAuthInterceptor()).addPathPatterns("/**");
    }
}
