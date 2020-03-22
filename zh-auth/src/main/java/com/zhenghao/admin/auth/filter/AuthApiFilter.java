package com.zhenghao.admin.auth.filter;


import com.zhenghao.admin.auth.entity.SysUserEntity;
import com.zhenghao.admin.auth.handler.RequestAuthHandler;
import com.zhenghao.admin.auth.service.SysUserService;
import com.zhenghao.admin.common.constant.HttpStatusConstant;
import com.zhenghao.admin.common.constant.SystemConstant;
import com.zhenghao.admin.common.context.BaseContextHandler;
import com.zhenghao.admin.common.entity.Result;
import com.zhenghao.admin.common.jwt.JWTInfo;
import com.zhenghao.admin.common.jwt.JWTTokenProcessor;
import com.zhenghao.admin.common.util.IPUtils;
import com.zhenghao.admin.common.util.ResponseUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.zhenghao.admin.common.constant.SystemConstant.API_PREFIX;

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
@WebFilter(filterName = "AuthApiFilter", urlPatterns = "/*")
public class AuthApiFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(AuthApiFilter.class);

    /**
     * 匹配api规则,不能重复且不能存在交集, 例：/uploads/-/system/user/avatar 不能与 /uploads 共存
     * 不符合规则的api均为无效api
     */
    private final List<String> matches;

    /**
     * JWT token header
     */
    private final String tokenHeader;

    private final SysUserService sysUserService;

    private final JWTTokenProcessor jwtTokenProcessor;

    private final RequestAuthHandler requestAuthHandler;

    public AuthApiFilter(List<String> matches,
                         String tokenHeader,
                         SysUserService sysUserService,
                         JWTTokenProcessor jwtTokenProcessor,
                         RequestAuthHandler requestAuthHandler) {
        this.matches = matches;
        this.tokenHeader = tokenHeader;
        this.sysUserService = sysUserService;
        this.jwtTokenProcessor = jwtTokenProcessor;
        this.requestAuthHandler = requestAuthHandler;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;

            String uri = httpServletRequest.getRequestURI();
            String method = httpServletRequest.getMethod();
            logger.info("request uri:[{}], method:[{}], from:[{}], check token and user permission", uri, method, IPUtils.getIpAddr());

            // 判断当前 uri 路由是否有效
            if (matches.stream().noneMatch(uri::startsWith)) {
                logger.warn("request uri:[{}], method:[{}], this api is invalid!", uri, method);
                ResponseUtils.setResultResponse(httpServletResponse, Result.ofFail(HttpStatusConstant.REQUEST_API_INVALID, "This api is invalid!"));
                return;
            }

            // 将 uri 替换为 权限uri
            String authPath = uri.replace(API_PREFIX, "");

            // 匿名访问过滤
            if (requestAuthHandler.validateAnnoFilterChain(authPath, method)) {
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
            if (requestAuthHandler.validateAuthFilterChain(authPath, method)) {
                setCurrentUserInfo(jwtInfo);
                chain.doFilter(request, response);
                return;
            }

            // 用户所拥有的权限的uri
            if (requestAuthHandler.validatePermsFilterChain(authPath, method, jwtInfo.getUserId())) {
                setCurrentUserInfo(jwtInfo);
                chain.doFilter(request, response);
                return;
            }

            logger.warn("request uri:[{}], method:[{}], User Forbidden!Does not has Permission!", uri, method);
            ResponseUtils.setResultResponse(httpServletResponse, Result.ofFail(HttpStatusConstant.USER_API_UNAUTHORIZED, "User Forbidden!Does not has Permission!"));
        }
    }

    private JWTInfo getJWTUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authToken;
        JWTInfo jwtInfo = null;
        authToken = getAuthToken(request);
        try {
            jwtInfo = jwtTokenProcessor.getInfoFromToken(authToken);
        } catch (ExpiredJwtException ex) {
            logger.error("User token expired!");
            ResponseUtils.setResultResponse(response, Result.ofFail(HttpStatusConstant.TOKEN_EXPIRED_FORBIDDEN, "User token expired!"));
        } catch (SignatureException ex) {
            logger.error("User token signature error!");
            ResponseUtils.setResultResponse(response, Result.ofFail(HttpStatusConstant.TOKEN_SIGNATURE_ERROR, "User token signature error!"));
        } catch (IllegalArgumentException ex) {
            logger.error("User token is null or empty!");
            ResponseUtils.setResultResponse(response, Result.ofFail(HttpStatusConstant.TOKEN_NULL_FORBIDDEN, "User token is null or empty!"));
        } catch (Exception ex) {
            logger.error("User token other exception!");
            ResponseUtils.setResultResponse(response, Result.ofFail(HttpStatusConstant.TOKEN_OTHER_EXCEPTION, "User token other exception!"));
        }
        return jwtInfo;
    }

    /**
     * 从 request 中获取 token ，顺序为 header -> parameter -> cookie
     *
     * @param request
     * @return
     */
    private String getAuthToken(HttpServletRequest request) {
        String authToken;
        authToken = request.getHeader(tokenHeader);
        if (StringUtils.isBlank(authToken)) {
            authToken = request.getParameter(tokenHeader);
        }
        if (StringUtils.isBlank(authToken)) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(tokenHeader)) {
                        authToken = cookie.getValue();
                    }
                }
            }
        }
        return authToken;
    }

    /**
     * 检测当前token用户信息
     *
     * @param jwtInfo
     * @param response
     * @return
     * @throws IOException
     */
    private boolean validateUserInfo(JWTInfo jwtInfo, HttpServletResponse response) throws IOException {
        SysUserEntity user = sysUserService.getUserEntityById(jwtInfo.getUserId());
        if (user == null) {
            logger.error("Token exception! Account does not exist!");
            ResponseUtils.setResultResponse(response, Result.ofFail(HttpStatusConstant.USER_UNKNOWN_ACCOUNT, "Token exception! Account does not exist!"));
            return false;
        }
        if (user.getStatus() == SystemConstant.StatusType.DISABLE.getValue()) {
            logger.error("Token exception! Account locked!");
            ResponseUtils.setResultResponse(response, Result.ofFail(HttpStatusConstant.USER_LOCKED_ACCOUNT, "Token exception! Account locked!"));
            return false;
        }
        return true;
    }


    /**
     * 记录当前请求token用户信息
     *
     * @param jwtInfo
     */
    private void setCurrentUserInfo(JWTInfo jwtInfo) {
        BaseContextHandler.setUsername(jwtInfo.getUsername());
        BaseContextHandler.setName(jwtInfo.getName());
        BaseContextHandler.setUserId(jwtInfo.getUserId());
    }
}
