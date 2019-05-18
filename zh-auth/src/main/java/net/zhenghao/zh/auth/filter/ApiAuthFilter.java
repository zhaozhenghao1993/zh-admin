package net.zhenghao.zh.auth.filter;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import net.zhenghao.zh.auth.config.RouteConfig;
import net.zhenghao.zh.auth.config.TokenHeaderConfig;
import net.zhenghao.zh.auth.core.RequestHandlerAdapter;
import net.zhenghao.zh.auth.dao.SysUserMapper;
import net.zhenghao.zh.auth.entity.SysUserEntity;
import net.zhenghao.zh.common.constant.HttpStatusConstant;
import net.zhenghao.zh.common.constant.SystemConstant;
import net.zhenghao.zh.common.context.BaseContextHandler;
import net.zhenghao.zh.common.entity.R;
import net.zhenghao.zh.common.entity.Result;
import net.zhenghao.zh.common.jwt.JWTInfo;
import net.zhenghao.zh.common.utils.JSONUtils;
import net.zhenghao.zh.common.utils.UserAuthUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 🙃
 * 🙃 api地址过滤器
 * 🙃 注：@order值越小越先执行
 * 将/api/v1/sys/user/info 转发至 /sys/user/info，禁止直接访问/user/info
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/01/13 19:55
 * GlobalFilter.java
 */
@Order(1)
@WebFilter(filterName = "ApiAuthFilter", urlPatterns = "/*")
public class ApiAuthFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(ApiAuthFilter.class);

    @Value("${zh-admin.api.prefix}")
    private String apiPrefix;

    @Autowired
    private RouteConfig routeConfig;

    @Autowired
    private TokenHeaderConfig tokenHeaderConfig;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private UserAuthUtils userAuthUtils;

    @Autowired
    private RequestHandlerAdapter requestHandlerAdapter;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;

            String uri = httpServletRequest.getRequestURI();
            String method = httpServletRequest.getMethod();
            logger.info("{}::{} ==> check token and user permission....", uri, method);

            // 判断当前 uri 路由是否有效
            if (!routeConfig.getRoutes().stream().anyMatch(route -> uri.startsWith(route))) {
                logger.error("{},This api is invalid!", uri);
                getErrorResponse(httpServletResponse, Result.ofFail(HttpStatusConstant.REQUEST_API_INVALID, "This api is invalid!"));
                return;
            }

            // 将 uri 替换为 权限uri
            String authPath = uri.replace(apiPrefix, "");

            // 匿名访问过滤
            if (requestHandlerAdapter.validateAnnoFilterChain(authPath, method)) {
                chain.doFilter(request, response);
                return;
            }

            // 获取用户信息
            JWTInfo jwtInfo = getJWTUser(httpServletRequest, httpServletResponse);
            if (jwtInfo == null) {
                return;
            }

            // 检测当前token用户信息
            if (!validateUserInfo(jwtInfo, httpServletResponse)) {
                return;
            }

            // 需要登陆token 且不权限拦截 即可访问
            if (requestHandlerAdapter.validateAuthFilterChain(authPath, method)) {
                setCurrentUserInfo(jwtInfo);
                chain.doFilter(request, response);
                return;
            }

            // 用户所拥有的权限的uri
            if (requestHandlerAdapter.validatePermsFilterChain(authPath, method, jwtInfo.getUserId())) {
                setCurrentUserInfo(jwtInfo);
                chain.doFilter(request, response);
                return;
            }

            logger.error("{}::{},User Forbidden!Does not has Permission!", uri, method);
            getErrorResponse(httpServletResponse, Result.ofFail(HttpStatusConstant.USER_API_UNAUTHORIZED, "User Forbidden!Does not has Permission!"));
            return;
        }
    }

    private JWTInfo getJWTUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authToken;
        JWTInfo jwtInfo = null;
        authToken = getAuthToken(request);
        try {
            jwtInfo = userAuthUtils.getInfoFromToken(authToken);
        } catch (ExpiredJwtException ex) {
            logger.error("User token expired!");
            getErrorResponse(response, Result.ofFail(HttpStatusConstant.TOKEN_EXPIRED_FORBIDDEN, "User token expired!"));
        } catch (SignatureException ex) {
            logger.error("User token signature error!");
            getErrorResponse(response, Result.ofFail(HttpStatusConstant.TOKEN_SIGNATURE_ERROR, "User token signature error!"));
        } catch (IllegalArgumentException ex) {
            logger.error("User token is null or empty!");
            getErrorResponse(response, Result.ofFail(HttpStatusConstant.TOKEN_NULL_FORBIDDEN, "User token is null or empty!"));
        } catch (Exception ex) {
            logger.error("User token other exception!");
            getErrorResponse(response, Result.ofFail(HttpStatusConstant.TOKEN_OTHER_EXCEPTION, "User token other exception!"));
        }
        return jwtInfo;
    }

    /**
     * 从 request 中获取 token ，顺序为 header -> parameter -> cookie
     * @param request
     * @return
     */
    private String getAuthToken (HttpServletRequest request) {
        String authToken;
        authToken = request.getHeader(tokenHeaderConfig.getTokenHeader());
        if (StringUtils.isBlank(authToken)) {
            authToken = request.getParameter(tokenHeaderConfig.getTokenHeader());
        }
        if (StringUtils.isBlank(authToken)) {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie: cookies) {
                if (cookie.getName().equals(tokenHeaderConfig.getTokenHeader())) {
                    authToken = cookie.getValue();
                }
            }
        }
        return authToken;
    }

    /**
     * 检测当前token用户信息
     * @param jwtInfo
     * @param response
     * @return
     * @throws IOException
     */
    private boolean validateUserInfo(JWTInfo jwtInfo, HttpServletResponse response) throws IOException {
        SysUserEntity user = sysUserMapper.getObjectById(jwtInfo.getUserId());
        if (user == null) {
            logger.error("Token exception! Account does not exist!");
            getErrorResponse(response, Result.ofFail(HttpStatusConstant.USER_UNKNOWN_ACCOUNT, "Token exception! Account does not exist!"));
            return false;
        }
        if (user.getStatus() == SystemConstant.StatusType.DISABLE.getValue()) {
            logger.error("Token exception! Account locked!");
            getErrorResponse(response, Result.ofFail(HttpStatusConstant.USER_LOCKED_ACCOUNT, "Token exception! Account locked!"));
            return false;
        }
        return true;
    }

    /**
     * 响应抛异常封装
     * @param response
     * @param result
     * @throws IOException
     */
    private void getErrorResponse(HttpServletResponse response, Result result) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter()
                .write(JSONUtils.objToString(result));
    }

    /**
     * 记录当前请求token用户信息
     * @param jwtInfo
     */
    private void setCurrentUserInfo(JWTInfo jwtInfo) {
        BaseContextHandler.setUsername(jwtInfo.getUsername());
        BaseContextHandler.setName(jwtInfo.getName());
        BaseContextHandler.setUserId(jwtInfo.getUserId());
    }
}
