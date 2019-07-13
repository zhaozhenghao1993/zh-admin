package com.zhenghao.admin.auth.handler;

import com.zhenghao.admin.auth.config.FilterChainConfig;
import com.zhenghao.admin.auth.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * 🙃
 * 🙃 请求权限处理
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/01/19 16:10
 * RequestAuthHandler.java
 */
@Component
public class RequestAuthHandler {

    @Autowired
    private FilterChainConfig filterChainConfig;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 验证当前uri是否为直接匿名访问uri
     *
     * @param uri
     * @param method
     * @return
     */
    public boolean validateAnnoFilterChain(String uri, String method) {
        boolean bool = false;
        if (filterChainConfig.getAnnoFilterChainList().stream().anyMatch(permission -> validate(uri, method, permission.getUri(), permission.getMethod()))) {
            bool = true;
        }
        return bool;
    }

    /**
     * 验证当前uri是否为 需要登陆token 且不权限拦截 即可访问
     *
     * @param uri
     * @param method
     * @return
     */
    public boolean validateAuthFilterChain(String uri, String method) {
        boolean bool = false;
        if (filterChainConfig.getAuthFilterChainList().stream().anyMatch(permission -> validate(uri, method, permission.getUri(), permission.getMethod()))) {
            bool = true;
        }
        return bool;
    }

    /**
     * 验证当前uri是否为当前用户所拥有的权限
     *
     * @param uri
     * @param method
     * @param userId
     * @return
     */
    public boolean validatePermsFilterChain(String uri, String method, Long userId) {
        boolean bool = false;
        if (sysUserService.listUserPerms(userId).stream().anyMatch(sysMenuEntity -> validate(uri, method, sysMenuEntity.getUri(), sysMenuEntity.getMethod()))) {
            bool = true;
        }
        return bool;
    }

    /**
     * 正则验证restful api 匹配
     * 注：2019-03-24 21:39 ==> 正则规则由 [a-zA-Z\\\\d] 改为 [\\d]
     * 为避免 /sys/user/enable :: PUT 匹配 /sys/user/{id} :: PUT 成功，造成权限混乱
     * 将正则改为 restful 只能匹配 {id} 数字
     * <p>
     * 注：2019-04-05 17:39 ==> 支持通配符 /sys/user/*
     *
     * @param requestUri    请求uri
     * @param requestMethod 请求method
     * @param uri           规则uri       例: /test/demo/{id}
     * @param method        规则method
     * @return
     */
    public static boolean validate(String requestUri, String requestMethod, String uri, String method) {
        if (uri.indexOf('{') >= 1) {
            uri = uri.replaceAll("\\{[^}]+\\}", "[\\\\d]+");
        }
        if (uri.indexOf('*') >= 1) {
            uri = uri.replaceAll("\\*", "[\\\\s\\\\S]*");
        }
        String regEx = "^" + uri + "$";
        return (Pattern.compile(regEx).matcher(requestUri).find())
                && method.equals(requestMethod);
    }
}
