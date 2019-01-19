package net.zhenghao.zh.auth.interceptor;

import net.zhenghao.zh.auth.core.RequestHandlerAdapter;
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

    @Autowired
    private RequestHandlerAdapter requestHandlerAdapter;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(request.getRequestURL());
        System.out.println(request.getHeader("ZH-TOKEN"));
        // return super.preHandle(request, response, handler);
        return requestHandlerAdapter.dealRequestHandler(request, response);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println(2);
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println(3);
        super.afterCompletion(request, response, handler, ex);
    }
}
