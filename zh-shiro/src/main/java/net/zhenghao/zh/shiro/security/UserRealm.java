package net.zhenghao.zh.shiro.security;

import net.zhenghao.zh.common.constant.SystemConstant;
import net.zhenghao.zh.common.entity.SysUserEntity;
import net.zhenghao.zh.common.utils.ShiroUtils;
import net.zhenghao.zh.shiro.manager.SysUserManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * 认证
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年12月5日 上午11:17:42
 * UserRealm.java
 */
public class UserRealm extends AuthorizingRealm {
	
	@Autowired
	private SysUserManager sysUserManager;

	/**
	 * 每次调用接口时判断当前用户是否具有该接口的权限(验证权限时调用)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Long userId = ShiroUtils.getUserId();
		//用户角色
		Set<String> roleSet = sysUserManager.listUserRoles(userId);
		//用户权限
		Set<String> permsSet = sysUserManager.listUserPerms(userId);
		//授权信息
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(roleSet);
		info.setStringPermissions(permsSet);
		/*roleSet.stream().forEach(string -> System.out.println(string));
		permsSet.stream().forEach(string -> System.out.println(string));*/
		return info;
	}

	
	/**
	 * 认证(登陆时调用)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();//得到用户名
		String password = new String((char[]) token.getCredentials());//得到密码
		String code = ShiroUtils.getCode(SystemConstant.TWO_FACTOR_AUTHENTICATOR);//得到双因素身份验证码
		
		//查询用户信息
		SysUserEntity user = sysUserManager.getByUserName(username);
		
		//账号不存在
		if (user == null) {
			throw new UnknownAccountException("账号或密码不正确");
		}
		if (!password.equals(user.getPassword())) {
			throw new IncorrectCredentialsException("账号或密码不正确");
		}
		//账号锁定
        if (user.getStatus() == 0) {
			throw new LockedAccountException("账号已被锁定,请联系管理员");
		}
        //认证信息(第一个参数,用于放置用户对象信息,同时可以通过securityutis.getsubject().getprincipal();取出)
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
		return info;
	}

}
