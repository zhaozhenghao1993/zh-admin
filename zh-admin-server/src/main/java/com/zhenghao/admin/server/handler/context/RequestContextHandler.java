package com.zhenghao.admin.server.handler.context;

import com.zhenghao.admin.server.entity.SysUserEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * 🙃
 * 🙃 当前请求管理端用户信息
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/01/13 21:21
 * BaseContextHandler.java
 */

public class RequestContextHandler {

    private RequestContextHandler() {
    }

    /**
     * 当前系统管理用户ID key
     */
    private static final String CURRENT_SYS_USER_ID = "currentSysUserId";

    /**
     * 当前系统管理用户username key
     */
    private static final String CURRENT_SYS_USER_USERNAME = "currentSysUserName";

    private final static ThreadLocal<Map<String, Object>> THREAD_LOCAL = new ThreadLocal<>();

    public static void set(String key, Object value) {
        Map<String, Object> map = THREAD_LOCAL.get();
        if (map == null) {
            map = new HashMap<>();
            THREAD_LOCAL.set(map);
        }
        map.put(key, value);
    }

    public static Object get(String key) {
        Map<String, Object> map = THREAD_LOCAL.get();
        if (map == null) {
            map = new HashMap<>();
            THREAD_LOCAL.set(map);
        }
        return map.get(key);
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }

    public static Long getUserId() {
        Object value = get(CURRENT_SYS_USER_ID);
        return (Long) value;
    }

    public static String getUsername() {
        Object value = get(CURRENT_SYS_USER_USERNAME);
        return returnObjectValue(value);
    }

    public static void setUserId(Long userId) {
        set(CURRENT_SYS_USER_ID, userId);
    }

    public static void setUsername(String username) {
        set(CURRENT_SYS_USER_USERNAME, username);
    }

    public static void setSysUser(SysUserEntity sysUser) {
        setUserId(sysUser.getId());
        setUsername(sysUser.getUsername());
    }

    private static String returnObjectValue(Object value) {
        return value == null ? null : value.toString();
    }
}
