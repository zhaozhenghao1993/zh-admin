package net.zhenghao.zh.auth.interceptor;

import net.zhenghao.zh.common.context.BaseContextHandler;
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
public class TokenAuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清空当前请求token用户信息
        BaseContextHandler.remove();
        super.afterCompletion(request, response, handler, ex);
    }
}
