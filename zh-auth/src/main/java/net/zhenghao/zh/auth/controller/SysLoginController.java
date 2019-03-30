package net.zhenghao.zh.auth.controller;

import net.zhenghao.zh.auth.entity.SysUserEntity;
import net.zhenghao.zh.auth.service.SysUserService;
import net.zhenghao.zh.common.constant.HttpStatusConstant;
import net.zhenghao.zh.common.entity.R;
import net.zhenghao.zh.common.jwt.JWTInfo;
import net.zhenghao.zh.common.utils.MD5Utils;
import net.zhenghao.zh.common.utils.UserAuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 用户controller
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2019年2月16日 下午3:21:53
 * SysLoginController.java
 */
@RestController
@RequestMapping("/sys")
public class SysLoginController {

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private UserAuthUtils userAuthUtils;

	@PostMapping("/login")
	public R login(@RequestBody Map<String, Object> params) {
		String username = params.get("username").toString();
		String password = params.get("password").toString();
		password = MD5Utils.encrypt(username, password);
		return validate(username, password);
	}

	private R validate(String username, String password) {
		// 查询用户信息
		SysUserEntity user = sysUserService.getUserByName(username);

		// 账号不存在
		if (user == null) {
			return R.error(HttpStatusConstant.USER_UNKNOWN_ACCOUNT, "Account does not exist!");
		}
		if (!password.equals(user.getPassword())) {
			return R.error(HttpStatusConstant.USER_INCORRECT_CREDENTIALS, "Account credentials incorrect!");
		}
		//账号锁定
		if (user.getStatus() == 1) {
			return R.error(HttpStatusConstant.USER_LOCKED_ACCOUNT, "Account locked!");
		}
		try {
			return R.ok().put("token", userAuthUtils.getTokenFromJWTInfo(new JWTInfo(user.getUserId(), user.getUsername())));
		} catch (Exception e) {
			return R.error(HttpStatusConstant.USER_AUTHENTICATION_EXCEPTION, "Account login exception!");
		}
	}
}
