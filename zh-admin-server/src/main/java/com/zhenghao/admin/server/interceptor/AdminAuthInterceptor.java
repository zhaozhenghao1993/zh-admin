package com.zhenghao.admin.server.interceptor;

import com.zhenghao.admin.server.handler.context.RequestContextHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 🙃
 * 🙃
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/01/13 21:50
 * TokenAuthInterceptor.java
 */
@Component
public class AdminAuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清空当前请求管理端用户信息
        RequestContextHandler.remove();
        super.afterCompletion(request, response, handler, ex);
    }
}
