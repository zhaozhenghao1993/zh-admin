package net.zhenghao.zh.common.util;

import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * IP地址
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2017年11月30日 下午2:59:05
 * IPUtils.java
 */
public class IPUtils {

    private IPUtils() {
    }

    private static final Logger logger = LoggerFactory.getLogger(IPUtils.class);

    public static final String LOCAL_IP_ADDRESS = "127.0.0.1";

    /**
     * 获取IP地址
     *
     * @param request
     * @return 使用Nginx等反向代理软件, 则不能通过request.getRemoteAddr()获取IP地址
     * 如果使用了多级反向代理的话,X-Forwarded-For的值并不止一个,而是一串IP地址,X-Forwarded-For
     * 中第一个非unknown的有效IP字符串，则为真实IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = null;
        String unknown = "unknown";
        String seperator = ",";
        int maxLength = 15;

        try {
            ip = request.getHeader("x-forwarded-for");
            if (StringUtils.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (StringUtils.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (StringUtils.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception e) {
            logger.error("IPUtils exception ", e);
        }

        //使用代理,则获取第一个IP地址
        if (StringUtils.isNotEmpty(ip) && ip.length() > maxLength) {
            int idx = ip.indexOf(seperator);
            if (idx > 0) {
                ip = ip.substring(0, idx);
            }
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? LOCAL_IP_ADDRESS : ip;
    }


    /**
     * 获取ip地址
     *
     * @return
     */
    public static String getIpAddr() {
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        return getIpAddr(request);
    }

    /**
     * 获取请求 Header 'User-Agent'
     * 操作系统和浏览器
     * // 获取客户端操作系统
     * String os = userAgent.getOperatingSystem().getName();
     * // 获取客户端浏览器
     * String browser = userAgent.getBrowser().getName();
     *
     * @return
     */
    public static UserAgent getUserAgent() {
        return UserAgent.parseUserAgentString(HttpContextUtils.getHttpServletRequest().getHeader("User-Agent"));
    }

    /**
     * 获取服务器内网IP
     *
     * @return
     */
    public static String getHostIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            logger.error("IPUtils get server host IP exception", e);
        }
        return LOCAL_IP_ADDRESS;
    }

    /**
     * 获取服务器内网名称
     *
     * @return
     */
    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            logger.error("IPUtils get server host name exception", e);
        }
        return "未知";
    }
}
