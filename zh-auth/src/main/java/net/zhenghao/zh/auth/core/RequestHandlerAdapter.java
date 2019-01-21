package net.zhenghao.zh.auth.core;

import net.zhenghao.zh.auth.config.FilterChainConfig;
import net.zhenghao.zh.common.jwt.JWTInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    /**
     * 验证当前uri是否为直接匿名访问uri
     * @param uri
     * @param method
     * @return
     */
    public boolean validateAnnoFilterChain(String uri, String method) {
        if (filterChainConfig.getAnnoFilterChain().stream().filter(permission -> uri.equals(permission.getUri()) && method.equals(permission.getMethod())).findAny().isPresent()) {
            return true;
        }
        return false;
    }

    public boolean validatePermsFilterChain(String uri, String method, String token) {
        if (filterChainConfig.getAnnoFilterChain().stream().filter(permission -> uri.equals(permission.getUri()) && method.equals(permission.getMethod())).findAny().isPresent()) {
            return true;
        }
        return false;
    }

    public boolean dealRequestHandler(HttpServletRequest request, HttpServletResponse response) {
        String requestUri = request.getRequestURI(); //得到请求的资源
        String requestmethod = request.getMethod(); //得到请求URL地址时使用的方法
        if (filterChainConfig.getAnnoFilterChain().stream().filter(permission -> requestUri.equals(permission.getUri()) && requestmethod.equals(permission.getMethod())).findAny().isPresent()) {
            return true;
        }


        return false;
    }

    public static boolean test(String requestUri, String requestMethod, String uri, String method) {
        // String uri = permissionInfo.getUri();
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

        System.out.println(test(requestUri, requestMethod, uri, method));
    }
}
