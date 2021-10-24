package com.zhenghao.admin.server.util;

import com.google.code.kaptcha.Constants;
import com.zhenghao.admin.common.constant.SystemConstants;
import com.zhenghao.admin.common.util.RSAUtils;
import com.zhenghao.admin.server.entity.SysUserEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 🙃
 * 🙃
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2020/05/22 21:38
 * SessionUtils.java
 */
public class SessionUtils {

    private SessionUtils() {
    }

    /**
     * 从session中获取 sys user 信息
     *
     * @param request
     * @return
     */
    public static SysUserEntity getSysUserFromSession(HttpServletRequest request) {
        return (SysUserEntity) getSession(request).getAttribute(SystemConstants.SYS_SESSION_USER);
    }

    /**
     * 在session中设置 sys user 信息
     *
     * @param request
     * @param sysUser
     */
    public static void setSysUserFromSession(HttpServletRequest request, SysUserEntity sysUser) {
        getSession(request).setAttribute(SystemConstants.SYS_SESSION_USER, sysUser);
    }

    /**
     * 在session中删除 sys user 信息
     *
     * @param request
     */
    public static void removeSysUserFromSession(HttpServletRequest request) {
        getSession(request).removeAttribute(SystemConstants.SYS_SESSION_USER);
    }

    /**
     * 获取验证码
     *
     * @param request
     */
    public static String getCaptchaFromSession(HttpServletRequest request) {
        return (String) getSession(request).getAttribute(Constants.KAPTCHA_SESSION_KEY);
    }

    public static void setCaptchaFromSession(HttpServletRequest request, String text) {
        getSession(request).setAttribute(Constants.KAPTCHA_SESSION_KEY, text);
    }

    public static void removeCaptchaFromSession(HttpServletRequest request) {
        getSession(request).removeAttribute(Constants.KAPTCHA_SESSION_KEY);
    }

    public static RSAUtils.RsaKey getRsaKeyFromSession(HttpServletRequest request) {
        return (RSAUtils.RsaKey) getSession(request).getAttribute(SystemConstants.SYS_SESSION_RSA_KEY);
    }

    public static void setRsaKeyFromSession(HttpServletRequest request, RSAUtils.RsaKey rsaKey) {
        getSession(request).setAttribute(SystemConstants.SYS_SESSION_RSA_KEY, rsaKey);
    }

    public static void removeRsaKeyFromSession(HttpServletRequest request) {
        getSession(request).removeAttribute(SystemConstants.SYS_SESSION_RSA_KEY);
    }


    /**
     * 返回当前请求的session 如果没有就创建一个，保证不要出现 NPE
     *
     * @param request
     * @return
     */
    public static HttpSession getSession(HttpServletRequest request) {
        return request.getSession(true);
    }
}
