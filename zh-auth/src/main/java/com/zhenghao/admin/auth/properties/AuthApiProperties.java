package com.zhenghao.admin.auth.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 🙃
 * 🙃 权限api配置类
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2020/03/22 16:15
 * AuthApiProperties.java
 */
@ConfigurationProperties(prefix = "zh-admin.auth.api")
public class AuthApiProperties {

    /**
     * api matching rules, There can be no overlap and no intersection
     * eg: '/uploads/-/system/user/avatar' and '/uploads' cannot coexist
     */
    private List<String> matches;

    /**
     * request token header
     * default value: ZH-TOKEN
     */
    private String header = "ZH-TOKEN";

    public List<String> getMatches() {
        return matches;
    }

    public void setMatches(List<String> matches) {
        this.matches = matches;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
