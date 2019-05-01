package net.zhenghao.zh.common.context;

import net.zhenghao.zh.common.constant.SystemConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * 🙃
 * 🙃 当前请求用户信息管理器
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/01/13 21:21
 * BaseContextHandler.java
 */

public class BaseContextHandler {

    private BaseContextHandler() {
    }

    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    public static void set(String key, Object value) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    public static Object get(String key){
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            threadLocal.set(map);
        }
        return map.get(key);
    }

    public static void remove(){
        threadLocal.remove();
    }

    public static Long getUserId(){
        Object value = get(SystemConstant.CONTEXT_KEY_USER_ID);
        return (Long) value;
    }

    public static String getUsername(){
        Object value = get(SystemConstant.CONTEXT_KEY_USERNAME);
        return returnObjectValue(value);
    }

    public static String getToken(){
        Object value = get(SystemConstant.CONTEXT_KEY_USER_TOKEN);
        return returnObjectValue(value);
    }

    public static void setToken(String token){set(SystemConstant.CONTEXT_KEY_USER_TOKEN, token);}

    public static void setUserId(Long userId){
        set(SystemConstant.CONTEXT_KEY_USER_ID, userId);
    }

    public static void setUsername(String username){
        set(SystemConstant.CONTEXT_KEY_USERNAME, username);
    }

    private static String returnObjectValue(Object value) {
        return value == null ? null : value.toString();
    }
}
