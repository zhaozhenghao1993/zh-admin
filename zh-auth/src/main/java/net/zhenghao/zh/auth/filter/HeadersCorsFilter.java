package net.zhenghao.zh.auth.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 🙃
 * 🙃 跨域Filter
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/02/20 21:25
 * HeadersCorsFilter.java
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@WebFilter(filterName = "headersCORSFilter", urlPatterns = "/*")
@Component
public class HeadersCorsFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeadersCorsFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpResp = (HttpServletResponse) servletResponse;
        httpResp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        httpResp.setHeader("Access-Control-Allow-Origin", "*");
        httpResp.setHeader("Access-Control-Allow-Credentials", "true");
        httpResp.setHeader("Access-Control-Max-Age", "3600");
        httpResp.setHeader("Access-Control-Allow-Headers", "*");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
