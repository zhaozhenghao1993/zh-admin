package com.zhenghao.admin.server.config;

import com.zhenghao.admin.server.interceptor.AdminAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 🙃
 * 🙃 WebApp 配置
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2020/05/20 10:31
 * WebAppConfig.java
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    private final AdminAuthInterceptor adminAuthInterceptor;

    public WebAppConfig(AdminAuthInterceptor adminAuthInterceptor) {
        this.adminAuthInterceptor = adminAuthInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminAuthInterceptor).addPathPatterns("/api/admin/**");
    }

}
