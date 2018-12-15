package net.zhenghao.zh.common.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * shiro MD5盐值加密
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年12月7日 上午9:28:54
 * MD5Utils.java
 */
public class MD5Utils {
	
	private static final String SALT = "ia1peP7z";//盐值需要唯一: 一般使用随机字符串或 user id
	
	private static final String ALGORITH_NAME = "md5";//加密方式
	
	private static final int HASH_ITERATIONS = 2;//加密的次数 
	
	/**
	 * 使用md5生成加密后的密码
	 * @param pswd
	 * @return
	 */
	public static String encrypt(String pswd) {
		String newPassword = new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(SALT), HASH_ITERATIONS).toHex();
		return newPassword;
	}
	
	/**
	 * 使用md5生成加密后的密码
	 * @param username
	 * @param pswd
	 * @return
	 */
	public static String encrypt(String username, String pswd) {
		String newPassword = new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(username + SALT), HASH_ITERATIONS).toHex();
		return newPassword;
	}
	
	public static void main(String[] args) {
		String password = "123";
		String username = "admin";
		System.out.println(encrypt(username, password));
	}

}
