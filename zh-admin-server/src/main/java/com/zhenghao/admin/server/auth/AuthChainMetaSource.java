package com.zhenghao.admin.server.auth;

import com.zhenghao.admin.server.service.SysUserService;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * 🙃
 * 🙃
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2020/05/25 19:33
 * AuthChainMetaSource.java
 */
public class AuthChainMetaSource {

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 可以直接匿名访问
     */
    private List<ChainMeta> nonAuthFilterChainMetaList = new ArrayList<>();

    /**
     * 需要登录token 且不权限拦截 即可访问
     */
    private List<ChainMeta> authFilterChainMetaList = new ArrayList<>();

    private final SysUserService sysUserService;

    public AuthChainMetaSource(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    /**
     * 验证不需要登录直接放行
     * @param uri
     * @param method
     * @return
     */
    public boolean validateNonAuthFilterChain(String uri, String method) {
        return nonAuthFilterChainMetaList.stream().anyMatch(chainMeta -> validateRequestAuth(uri, method, chainMeta.getUriPattern(), chainMeta.getMethod()));
    }

    /**
     * 需要登录token 且不权限拦截 即可访问
     * @param uri
     * @param method
     * @return
     */
    public boolean validateAuthFilterChain(String uri, String method) {
        return authFilterChainMetaList.stream().anyMatch(chainMeta -> validateRequestAuth(uri, method, chainMeta.getUriPattern(), chainMeta.getMethod()));
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
        if (sysUserService.listUserPerms(userId).stream().anyMatch(sysMenuEntity -> validateRequestAuth(uri, method, sysMenuEntity.getUri(), sysMenuEntity.getMethod()))) {
            bool = true;
        }
        return bool;
    }

    /**
     * 请求权限校验
     * @param requestUri
     * @param requestMethod
     * @param uriPattern
     * @param method
     * @return
     */
    private boolean validateRequestAuth(String requestUri, String requestMethod, String uriPattern, String method) {
        return antPathMatcher.match(uriPattern, requestUri) && method.equals(requestMethod);
    }

    public void setNonAuthFilterChainMetaList(List<ChainMeta> nonAuthFilterChainMetaList) {
        this.nonAuthFilterChainMetaList = nonAuthFilterChainMetaList;
    }

    public void setAuthFilterChainMetaList(List<ChainMeta> authFilterChainMetaList) {
        this.authFilterChainMetaList = authFilterChainMetaList;
    }
}
