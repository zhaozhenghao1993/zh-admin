package net.zhenghao.zh.auth.interceptor;

import net.zhenghao.zh.auth.core.RequestHandlerAdapter;
import net.zhenghao.zh.common.context.BaseContextHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("当前token用户id" + BaseContextHandler.getUserId());
        System.out.println("当前token用户username" + BaseContextHandler.getUsername());
        BaseContextHandler.remove();
        System.out.println("删除后当前token用户id" + BaseContextHandler.getUserId());
        System.out.println("删除后当前token用户username" + BaseContextHandler.getUsername());
        super.afterCompletion(request, response, handler, ex);
    }
}
