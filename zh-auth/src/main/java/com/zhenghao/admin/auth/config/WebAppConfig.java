package com.zhenghao.admin.auth.config;

import com.zhenghao.admin.auth.filter.AuthApiFilter;
import com.zhenghao.admin.auth.filter.XssFilter;
import com.zhenghao.admin.auth.handler.RequestAuthHandler;
import com.zhenghao.admin.auth.interceptor.TokenAuthInterceptor;
import com.zhenghao.admin.auth.properties.AuthApiProperties;
import com.zhenghao.admin.auth.service.SysUserService;
import com.zhenghao.admin.common.jwt.JWTTokenProcessor;
import com.zhenghao.admin.common.properties.JWTProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 🙃
 * 🙃 spring mvc 注册配置类
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/01/13 21:54
 * WebAppConfig.java
 */
@Configuration
@EnableConfigurationProperties(AuthApiProperties.class)
public class WebAppConfig implements WebMvcConfigurer {

    private final AuthApiProperties authApiProperties;

    @Autowired
    public WebAppConfig(AuthApiProperties authApiProperties) {
        this.authApiProperties = authApiProperties;
    }

    /**
     * XSS过滤器
     * 包装 request
     * @return
     */
    @Bean
    public XssFilter xssFilter() {
        return new XssFilter();
    }

    @Bean
    public AuthApiFilter authApiFilter(SysUserService sysUserService,
                                       JWTTokenProcessor jwtTokenProcessor,
                                       RequestAuthHandler requestAuthHandler) {
        return new AuthApiFilter(authApiProperties.getMatches(),
                authApiProperties.getHeader(), sysUserService, jwtTokenProcessor, requestAuthHandler);
    }

    /**
     * 配置拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenAuthInterceptor()).addPathPatterns("/**");
    }
}
