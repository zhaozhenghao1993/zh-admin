package com.zhenghao.admin.auth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 🙃
 * 🙃 有效路由配置类
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/05/18 13:51
 * RouteConfig.java
 */

@Configuration
@ConfigurationProperties(prefix = "zh-admin.auth")
public class RouteConfig {

    private List<String> routes;

    public List<String> getRoutes() {
        return routes;
    }

    public void setRoutes(List<String> routes) {
        this.routes = routes;
    }
}
