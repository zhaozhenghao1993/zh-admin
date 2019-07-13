package com.zhenghao.admin.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 🙃
 * 🙃 请求header配置类
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/01/19 15:22
 * TokenConfig.java
 */
@Configuration
public class TokenHeaderConfig {

    @Value("${zh-admin.jwt.token-header}")
    private String tokenHeader;

    public String getTokenHeader() {
        return tokenHeader;
    }

    public void setTokenHeader(String tokenHeader) {
        this.tokenHeader = tokenHeader;
    }
}
