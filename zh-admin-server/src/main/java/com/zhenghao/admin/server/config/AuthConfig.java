package com.zhenghao.admin.server.config;

import com.zhenghao.admin.server.auth.AuthChainMetaSource;
import com.zhenghao.admin.server.auth.ChainMeta;
import com.zhenghao.admin.server.service.SysUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 🙃
 * 🙃
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2020/05/25 20:11
 * AuthConfig.java
 */
@Configuration
public class AuthConfig {

    @Bean
    public AuthChainMetaSource authChainMetaSource(SysUserService sysUserService) {
        AuthChainMetaSource authChainMetaSource = new AuthChainMetaSource(sysUserService);

        List<ChainMeta> nonAuthFilterChainMetaList = new ArrayList<>();
        nonAuthFilterChainMetaList.add(new ChainMeta("/api/admin/sys/login", "POST"));
        nonAuthFilterChainMetaList.add(new ChainMeta("/api/admin/sys/captcha.jpg", "GET"));
        nonAuthFilterChainMetaList.add(new ChainMeta("/api/admin/sys/rsa/key", "GET"));
        nonAuthFilterChainMetaList.add(new ChainMeta("/api/admin/sys/logout", "POST"));
        authChainMetaSource.setNonAuthFilterChainMetaList(nonAuthFilterChainMetaList);

        List<ChainMeta> authFilterChainMetaList = new ArrayList<>();
        authFilterChainMetaList.add(new ChainMeta("/api/admin/sys/user/info", "GET"));
        authFilterChainMetaList.add(new ChainMeta("/api/admin/sys/user/profile", "PUT"));
        authFilterChainMetaList.add(new ChainMeta("/api/admin/sys/user/profile/avatar", "POST"));
        authFilterChainMetaList.add(new ChainMeta("/api/admin/sys/user/profile/password", "PUT"));
        authFilterChainMetaList.add(new ChainMeta("/api/admin/sys/user/profile/theme", "PUT"));
        authFilterChainMetaList.add(new ChainMeta("/api/admin/sys/user/profile/color", "PUT"));
        authFilterChainMetaList.add(new ChainMeta("/api/admin/monitor/log/visit", "GET"));
        authFilterChainMetaList.add(new ChainMeta("/api/admin/monitor/log/visit/week", "GET"));
        // 头像地址
        authFilterChainMetaList.add(new ChainMeta("/uploads/system/user/avatar/{id}/**", "GET"));
        authChainMetaSource.setAuthFilterChainMetaList(authFilterChainMetaList);
        return authChainMetaSource;
    }
}
