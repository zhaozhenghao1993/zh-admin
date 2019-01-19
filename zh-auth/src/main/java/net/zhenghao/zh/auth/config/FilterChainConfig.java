package net.zhenghao.zh.auth.config;

import net.zhenghao.zh.auth.entity.SysPermissionEntity;
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
    private List<SysPermissionEntity> annoFilterChainList = new ArrayList<SysPermissionEntity>();

    @PostConstruct
    public void init() {
        annoFilterChainList.add(new SysPermissionEntity("/sys/login", "POST"));
        annoFilterChainList.add(new SysPermissionEntity("/test/demo", "GET"));
    }

    public List<SysPermissionEntity> getAnnoFilterChain() {
        return annoFilterChainList;
    }

    public void setAnnoFilterChain(List<SysPermissionEntity> annoFilterChainList) {
        this.annoFilterChainList = annoFilterChainList;
    }
}
