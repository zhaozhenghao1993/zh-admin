package net.zhenghao.zh.common.utils;

import net.zhenghao.zh.common.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Shiro工具类
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年11月22日 下午3:09:10
 * ShiroUtils.java
 */
public class ShiroUtils {
	
	public static Session getSession(){
		return SecurityUtils.getSubject().getSession();
	}
	
	public static Subject getSubject(){
		return SecurityUtils.getSubject();
	}
	
	/**
	 * 获取当前用户对象
	 * @return
	 */
	public static SysUserEntity getUserEntity(){
		return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
	}
	
	/**
	 * 获取当前用户id
	 * @return
	 */
	public static Long getUserId(){
		return getUserEntity().getUserId();
	}
	
	public static void setSessionAttribute(Object key, Object value) {
		getSession().setAttribute(key, value);
	}
	
	public static Object getSessionAttribute(Object key) {
		return getSession().getAttribute(key);
	}
	
	public static boolean isLogin(){
		return SecurityUtils.getSubject().getPrincipal() != null;
	}
	
	/**
	 * 退出登录
	 */
	public static void logout(){
		SecurityUtils.getSubject().logout();
	}
	
	/**
	 * 验证码
	 * @param key
	 * @return
	 */
	public static String getKaptcha(String key){
		String kaptcha = getSessionAttribute(key).toString();
		getSession().removeAttribute(key);
		return kaptcha;
	}

	/**
	 * 双因素身份验证码
	 * @param key
	 * @return
	 */
	public static String getCode(String key){
		String code = getSessionAttribute(key).toString();
		getSession().removeAttribute(key);
		return code;
	}
}

/**
 * 通过org.apache.shiro.SecurityUtils可以查询当前执行用户：
 * Subject currentUser = SecurityUtils.getSubject();
 * 
 * 获得对象，如果你这登录的时候是存储的的对象，那么你强转即可
 * SecurityUtils.getSubject().getPrincipal()
 */
