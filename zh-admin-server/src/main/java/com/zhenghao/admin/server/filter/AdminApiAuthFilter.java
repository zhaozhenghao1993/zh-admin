package com.zhenghao.admin.server.filter;


import com.zhenghao.admin.common.constant.HttpStatusConstants;
import com.zhenghao.admin.common.entity.Result;
import com.zhenghao.admin.server.auth.AuthChainMetaSource;
import com.zhenghao.admin.server.entity.SysUserEntity;
import com.zhenghao.admin.server.handler.context.RequestContextHandler;
import com.zhenghao.admin.server.service.SysUserService;
import com.zhenghao.admin.server.util.IPUtils;
import com.zhenghao.admin.server.util.ResponseUtils;
import com.zhenghao.admin.server.util.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 🙃
 * 🙃 api权限过滤器
 * 🙃 注：@order值越小越先执行
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/01/13 19:55
 * GlobalFilter.java
 */
@Order(1)
@WebFilter(filterName = "AdminApiAuthFilter", urlPatterns = {"/api/admin/*"})
public class AdminApiAuthFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(AdminApiAuthFilter.class);

    private final AuthChainMetaSource authChainMetaSource;

    public AdminApiAuthFilter(AuthChainMetaSource authChainMetaSource) {
        this.authChainMetaSource = authChainMetaSource;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String uri = httpServletRequest.getRequestURI();
        String method = httpServletRequest.getMethod();
        logger.info("request uri:[{}], method:[{}], from:[{}], check token and user permission", uri, method, IPUtils.getIpAddress(httpServletRequest));

        // 匿名访问过滤
        if (authChainMetaSource.validateNonAuthFilterChain(uri, method)) {
            chain.doFilter(request, response);
            return;
        }

        // 获取用户信息
        SysUserEntity sysUserFromSession = SessionUtils.getSysUserFromSession(httpServletRequest);
        if (sysUserFromSession == null) {
            // 没有权限
            ResponseUtils.setResultResponse(httpServletResponse, Result.ofFail(HttpStatusConstants.USER_API_UNAUTHORIZED, "请登陆后访问!"));
            return;
        }

        // 需要登陆 且不权限拦截 即可访问
        if (authChainMetaSource.validateAuthFilterChain(uri, method)) {
            RequestContextHandler.setSysUser(sysUserFromSession);
            chain.doFilter(request, response);
            return;
        }

        // 用户所拥有的权限的uri
        if (authChainMetaSource.validatePermsFilterChain(uri, method, sysUserFromSession.getId())) {
            RequestContextHandler.setSysUser(sysUserFromSession);
            chain.doFilter(request, response);
            return;
        }

        logger.warn("request uri:[{}], method:[{}], User Forbidden!Does not has Permission!", uri, method);
        ResponseUtils.setResultResponse(httpServletResponse, Result.ofFail(HttpStatusConstants.USER_API_FORBIDDEN, "此请求没有权限，请联系管理员开通!"));
    }

}
