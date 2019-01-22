package net.zhenghao.zh.auth.filter;


import net.zhenghao.zh.auth.config.TokenHeaderConfig;
import net.zhenghao.zh.auth.core.RequestHandlerAdapter;
import net.zhenghao.zh.common.constant.HttpStatusConstant;
import net.zhenghao.zh.common.entity.R;
import net.zhenghao.zh.common.jwt.JWTInfo;
import net.zhenghao.zh.common.utils.JSONUtils;
import net.zhenghao.zh.common.utils.JWTTokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger LOGGER = LoggerFactory.getLogger(ApiAuthFilter.class);

    @Value("${zh-admin.auth.routes}")
    private String routes;

    @Autowired
    private TokenHeaderConfig tokenHeaderConfig;

    @Autowired
    private JWTTokenUtils jwtTokenUtils;

    @Autowired
    private RequestHandlerAdapter requestHandlerAdapter;

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.info("check token and user permission....");
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            String uri = httpServletRequest.getRequestURI();
            String method = httpServletRequest.getMethod();

            if (!uri.startsWith(routes)) {
                LOGGER.error("{},This api is invalid!", uri);
                getErrorResponse(httpServletResponse, R.error(HttpStatusConstant.REQUEST_API_INVALID, "This api is invalid!"));
                return;
            }

            String newPath = uri.replace(routes, "");

            // 匿名访问过滤
            if (requestHandlerAdapter.validateAnnoFilterChain(newPath, method)) {
                RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher(newPath);
                requestDispatcher.forward(request, response);
                return;
            }

            requestHandlerAdapter.validateAnnoFilterChain(uri, method);
        }
    }

    @Override
    public void destroy() {

    }

    private JWTInfo getJWTUser(HttpServletRequest request) {
        String authToken = null;
        authToken = request.getHeader(tokenHeaderConfig.getTokenHeader());
        return null;
    }

    /**
     * 响应抛异常封装
     * @param response
     * @param r
     * @throws IOException
     */
    private void getErrorResponse(HttpServletResponse response, R r) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter()
                .write(JSONUtils.objToString(r));
    }
}
