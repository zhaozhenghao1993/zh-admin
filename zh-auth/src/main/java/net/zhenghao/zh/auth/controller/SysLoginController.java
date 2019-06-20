package net.zhenghao.zh.auth.controller;

import net.zhenghao.zh.auth.entity.SysUserEntity;
import net.zhenghao.zh.auth.service.SysUserService;
import net.zhenghao.zh.auth.vo.SysLoginVO;
import net.zhenghao.zh.common.constant.HttpStatusConstant;
import net.zhenghao.zh.common.entity.Result;
import net.zhenghao.zh.common.jwt.JWTInfo;
import net.zhenghao.zh.common.util.MD5Utils;
import net.zhenghao.zh.common.util.UserAuthUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
@RequestMapping("${zh-admin.api.prefix}/sys")
public class SysLoginController {

	@Resource
	private SysUserService sysUserService;

	@Resource
	private UserAuthUtils userAuthUtils;

	@PostMapping("/login")
	public Result<SysLoginVO> login(@RequestBody Map<String, Object> params) {
		String username = params.get("username").toString();
		String password = params.get("password").toString();
		password = MD5Utils.encrypt(username, password);
		return validate(username, password);
	}

	private Result<SysLoginVO> validate(String username, String password) {
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			return Result.ofFail(HttpStatusConstant.EXCEPTION_OTHER_CODE, "Invalid username or password!");
		}
		// 查询用户信息
		SysUserEntity user = sysUserService.getUserByName(username);

		// 账号不存在
		if (user == null) {
			return Result.ofFail(HttpStatusConstant.USER_UNKNOWN_ACCOUNT, "Account does not exist!");
		}
		if (!password.equals(user.getPassword())) {
			return Result.ofFail(HttpStatusConstant.USER_INCORRECT_CREDENTIALS, "Account credentials incorrect!");
		}
		//账号锁定
		if (user.getStatus() == 1) {
			return Result.ofFail(HttpStatusConstant.USER_LOCKED_ACCOUNT, "Account locked!");
		}
		try {
			return Result.ofSuccess(new SysLoginVO(userAuthUtils.getTokenFromJWTInfo(new JWTInfo(user.getId(), user.getUsername(), user.getName()))));
		} catch (Exception e) {
			return Result.ofFail(HttpStatusConstant.USER_AUTHENTICATION_EXCEPTION, "Account login exception!");
		}
	}
}
