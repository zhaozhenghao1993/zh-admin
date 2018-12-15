package net.zhenghao.zh.shiro.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.zhenghao.zh.common.entity.R;
import net.zhenghao.zh.common.utils.JSONUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * 🙃 登录拦截器
 * 🙃 重写FormAuthenticationFilter.java onAccessDenied方法，使其未登录不再重定向到login页面，全部交由前端处理
 * 🙃 只返回响应json
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2018/12/15 21:27
 * ShiroLoginFilter.java
 */

public class ShiroLoginFilter extends FormAuthenticationFilter {

    private static Logger LOGGER = LoggerFactory.getLogger(ShiroLoginFilter.class);

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        LOGGER.info("Login authentication is invalid, please login again!");
        //取消重定向，均返回json
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter()
                .write(JSONUtils.objToString(R.error(403, "Login authentication is invalid, please login again!")));
        return false;
    }
}
