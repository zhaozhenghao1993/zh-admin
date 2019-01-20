package net.zhenghao.zh.auth.filter;


import net.zhenghao.zh.auth.core.RequestHandlerAdapter;
import net.zhenghao.zh.common.entity.R;
import net.zhenghao.zh.common.utils.JSONUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 🙃
 * 🙃 api地址过滤器
 * 🙃 注：@order值越小越先执行
 * 将/api/v1/user/info 转发至 /user/info，禁止直接访问/user/info
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/01/13 19:55
 * GlobalFilter.java
 */
@Order(1)
@WebFilter(filterName = "ApiAuthFilter", urlPatterns = "/*")
@Component
public class ApiAuthFilter implements Filter {

    @Value("${zh-admin.auth.routes}")
    private String routes;

    @Autowired
    private RequestHandlerAdapter requestHandlerAdapter;

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String uri = httpServletRequest.getRequestURI();
            if (uri.startsWith(routes)) {
                String newPath = uri.replace(routes, "");
                // requestHandlerAdapter.dealRequestHandler();
                RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher(newPath);
                requestDispatcher.forward(request, response);
            } else {
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.setContentType("application/json");
                httpServletResponse.getWriter()
                        .write(JSONUtils.objToString(R.error(HttpStatus.SC_NOT_FOUND, "This api is invalid!")));
            }
        }
    }

    @Override
    public void destroy() {

    }
}
