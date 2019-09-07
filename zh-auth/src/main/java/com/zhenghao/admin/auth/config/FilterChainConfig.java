package com.zhenghao.admin.auth.config;

import com.zhenghao.admin.auth.entity.SysPermissionEntity;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 🙃
 * 🙃 过滤链配置
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/01/19 21:20
 * FilterChainConfig.java
 */
@Configuration
public class FilterChainConfig {

    /**
     * 可以直接匿名访问
     */
    private List<SysPermissionEntity> annoFilterChainList = new ArrayList<>();

    /**
     * 需要登陆token 且不权限拦截 即可访问
     */
    private List<SysPermissionEntity> authFilterChainList = new ArrayList<>();

    @PostConstruct
    public void init() {
        annoFilterChainList.add(new SysPermissionEntity("/sys/login", "POST"));
        annoFilterChainList.add(new SysPermissionEntity("/test/heart", "GET"));
        annoFilterChainList.add(new SysPermissionEntity("/test/ip", "GET"));
        annoFilterChainList.add(new SysPermissionEntity("/test/exception", "GET"));

        authFilterChainList.add(new SysPermissionEntity("/sys/user/info", "GET"));
        authFilterChainList.add(new SysPermissionEntity("/sys/user/profile", "PUT"));
        authFilterChainList.add(new SysPermissionEntity("/sys/user/profile/avatar", "POST"));
        authFilterChainList.add(new SysPermissionEntity("/sys/user/profile/password", "PUT"));
        authFilterChainList.add(new SysPermissionEntity("/sys/user/profile/theme", "PUT"));
        authFilterChainList.add(new SysPermissionEntity("/sys/user/profile/color", "PUT"));
        authFilterChainList.add(new SysPermissionEntity("/monitor/log/visit", "GET"));
        authFilterChainList.add(new SysPermissionEntity("/monitor/log/visit/week", "GET"));
        authFilterChainList.add(new SysPermissionEntity("/uploads/-/system/user/avatar/{id}/*", "GET")); // 头像地址
    }

    public List<SysPermissionEntity> getAnnoFilterChainList() {
        return annoFilterChainList;
    }

    public void setAnnoFilterChainList(List<SysPermissionEntity> annoFilterChainList) {
        this.annoFilterChainList = annoFilterChainList;
    }

    public List<SysPermissionEntity> getAuthFilterChainList() {
        return authFilterChainList;
    }

    public void setAuthFilterChainList(List<SysPermissionEntity> authFilterChainList) {
        this.authFilterChainList = authFilterChainList;
    }
}
