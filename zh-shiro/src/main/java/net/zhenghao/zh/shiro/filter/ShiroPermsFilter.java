package net.zhenghao.zh.shiro.filter;

import net.zhenghao.zh.common.entity.R;
import net.zhenghao.zh.common.utils.JSONUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * 🙃 权限拦截器
 * 🙃 PermissionsAuthorizationFilter.java onAccessDenied方法，使其虽然登录但未有Url权限，全部交由前端处理
 * 🙃 只返回响应json
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2018/12/15 22:55
 * ShiroPermsFilter.java
 */

public class ShiroPermsFilter extends PermissionsAuthorizationFilter {

    private static Logger LOGGER = LoggerFactory.getLogger(PermissionsAuthorizationFilter.class);

    /**
     * shiro认证perms资源失败后回调方法
     * @param request
     * @param response
     * @param mappedValue
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        LOGGER.info("The current account has no permissions!");
        //取消重定向，均返回json
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter()
                .write(JSONUtils.objToString(R.error(401, "The current account has no permissions!")));
        return false;
    }
}
