package net.zhenghao.zh.auth.core;

import net.zhenghao.zh.auth.config.FilterChainConfig;
import net.zhenghao.zh.auth.entity.SysMenuEntity;
import net.zhenghao.zh.auth.service.SysUserService;
import net.zhenghao.zh.common.jwt.JWTInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 🙃
 * 🙃 请求处理适配器
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/01/19 16:10
 * RequestHandlerAdapter.java
 */
@Component
public class RequestHandlerAdapter {

    @Autowired
    private FilterChainConfig filterChainConfig;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 验证当前uri是否为直接匿名访问uri
     * @param uri
     * @param method
     * @return
     */
    public boolean validateAnnoFilterChain(String uri, String method) {
        if (filterChainConfig.getAnnoFilterChainList().stream().filter(permission -> validate(uri, method, permission.getUri(), permission.getMethod())).findAny().isPresent()) {
            return true;
        }
        return false;
    }

    /**
     * 验证当前uri是否为 需要登陆token 且不权限拦截 即可访问
     * @param uri
     * @param method
     * @return
     */
    public boolean validateAuthFilterChain(String uri, String method) {
        if (filterChainConfig.getAuthFilterChainList().stream().filter(permission -> validate(uri, method, permission.getUri(), permission.getMethod())).findAny().isPresent()) {
            return true;
        }
        return false;
    }

    /**
     * 验证当前uri是否为当前用户所拥有的权限
     * @param uri
     * @param method
     * @param userId
     * @return
     */
    public boolean validatePermsFilterChain(String uri, String method, Long userId) {
        if (sysUserService.listUserPerms(userId).stream().filter(sysMenuEntity -> validate(uri, method, sysMenuEntity.getUri(), sysMenuEntity.getMethod())).findAny().isPresent()) {
            return true;
        }
        return false;
    }

    public boolean dealRequestHandler(HttpServletRequest request, HttpServletResponse response) {
        String requestUri = request.getRequestURI(); //得到请求的资源
        String requestmethod = request.getMethod(); //得到请求URL地址时使用的方法
        if (filterChainConfig.getAnnoFilterChainList().stream().filter(permission -> requestUri.equals(permission.getUri()) && requestmethod.equals(permission.getMethod())).findAny().isPresent()) {
            return true;
        }


        return false;
    }

    /**
     * 正则验证restful api 匹配
     * @param requestUri        请求uri
     * @param requestMethod     请求method
     * @param uri               规则uri       例: /test/demo/{id}
     * @param method            规则method
     * @return
     */
    public static boolean validate(String requestUri, String requestMethod, String uri, String method) {
        if (uri.indexOf("{") > 0) {
            uri = uri.replaceAll("\\{[^}]+\\}", "[a-zA-Z\\\\d]+");
        }
        String regEx = "^" + uri + "$";
        return (Pattern.compile(regEx).matcher(requestUri).find())
                && method.equals(requestMethod);
    }

    public static void main(String[] args) {
        String requestUri = "/user/1/edit/haha";
        String requestMethod = "GET";
        String uri = "/user/{id}/edit/{name}";
        String method = "GET";

        System.out.println(validate(requestUri, requestMethod, uri, method));
    }
}
