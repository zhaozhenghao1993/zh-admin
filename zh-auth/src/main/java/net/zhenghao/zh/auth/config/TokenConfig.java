package net.zhenghao.zh.auth.config;

import org.springframework.beans.factory.annotation.Value;

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

public class TokenConfig {

    @Value("${zh-admin.jwt.token-header}")
    private String tokenHeader;

    public String getTokenHeader() {
        return tokenHeader;
    }

    public void setTokenHeader(String tokenHeader) {
        this.tokenHeader = tokenHeader;
    }
}
