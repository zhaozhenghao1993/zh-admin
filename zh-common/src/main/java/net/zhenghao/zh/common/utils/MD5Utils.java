package net.zhenghao.zh.common.utils;

import net.zhenghao.zh.common.crypto.hash.SimpleHash;
import net.zhenghao.zh.common.crypto.util.ByteSource;
import org.apache.commons.lang3.StringUtils;

/**
 * MD5盐值加密
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
	 * @param password
	 * @return
	 */
	public static String encrypt(String password) {
		return new SimpleHash(ALGORITH_NAME, password, ByteSource.Util.bytes(SALT), HASH_ITERATIONS).toHex();
	}
	
	/**
	 * 使用md5生成加密后的密码
	 * @param username
	 * @param password
	 * @return
	 */
	public static String encrypt(String username, String password) {
		if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)) {
			return new SimpleHash(ALGORITH_NAME, password, ByteSource.Util.bytes(username + SALT), HASH_ITERATIONS).toHex();
		}
		return null;
	}

}
