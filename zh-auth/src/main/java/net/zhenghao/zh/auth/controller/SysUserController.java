package net.zhenghao.zh.auth.controller;

import net.zhenghao.zh.auth.entity.SysUserEntity;
import net.zhenghao.zh.common.annotation.SysLog;
import net.zhenghao.zh.common.constant.SystemConstant;
import net.zhenghao.zh.common.controller.AbstractController;
import net.zhenghao.zh.common.entity.Page;
import net.zhenghao.zh.common.entity.R;
import net.zhenghao.zh.auth.service.SysUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 系统用户controller
 * @RequestParam 用来处理Content-Type: 为 application/x-www-form-urlencoded编码的内容
 * @RequestBody	处理HttpEntity传递过来的数据，一般用来处理非Content-Type: application/x-www-form-urlencoded编码格式的数据。
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年12月8日 下午2:03:22
 * SysMenuController.java
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {

	@Resource
	private SysUserService sysUserService;

    /**
     * 获取登录用户信息
     * @return
     */
    @GetMapping("/info")
    public R info() {
        return sysUserService.getUserInfo(getUserId());
    }

	/**
	 * 获取登录用户权限
	 * @return
	 */
	@GetMapping("/button")
	public R listUserButton() {
		return sysUserService.listUserButton(getUserId());
	}

	/**
	 * 获取登录用户权限
	 * @return
	 */
	@GetMapping("/menu")
	public R menu() {
		return sysUserService.listUserMenu(getUserId());
	}

	/**
	 * 获取用户信息列表
	 * @return
	 */
	@GetMapping("")
	public Page<SysUserEntity> list(@RequestParam Map<String, Object> params) {
		if (getUserId() != SystemConstant.SUPER_ADMIN) {
			params.put("creatorId", getUserId());
		}
		return sysUserService.listUser(params);
	}

	/**
	 * 获取用户信息
	 * @param userId
	 * @return
	 */
	@GetMapping("/{id}")
	public R info(@PathVariable("id") Long userId) {
		return sysUserService.getUserById(userId);
	}

	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	@SysLog("新增用户")
	@PostMapping("")
	public R save(@RequestBody SysUserEntity user) {
		user.setCreatorId(getUserId());
		return sysUserService.saveUser(user);
	}

	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	@SysLog("修改用户")
	@PutMapping("/{id}")
	public R edit(@PathVariable("id") Long userId, @RequestBody SysUserEntity user) {
		user.setUserId(userId);
		user.setModifierId(getUserId());
		return sysUserService.updateUser(user);
	}

	/**
	 * 删除
	 * @param userId
	 * @return
	 */
	@SysLog("删除用户")
	@DeleteMapping("/{id}")
	public R remove(@PathVariable("id") Long userId) {
		return sysUserService.removeUser(userId);
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@SysLog("批量删除用户")
	@DeleteMapping("")
	public R batchRemove(@RequestBody Long[] ids) {
		return sysUserService.batchRemove(ids);
	}

	/**
	 * 用户修改密码
	 * @param pswd
	 * @param newPswd
	 * @return
	 */
	@SysLog("修改密码")
	@RequestMapping("/updatePswd")
	public R updatePswdByUser(String pswd, String newPswd) {
		SysUserEntity user = (SysUserEntity) sysUserService.getUserById(getUserId()).get("data");
		user.setPassword(pswd);//原密码
		user.setEmail(newPswd);//邮箱临时存储新密码
		return sysUserService.updatePswdByUser(user);
	}

	/**
	 * 启用账户
	 * @param ids
	 * @return
	 */
	@SysLog("启用账号")
	@PutMapping("/enable")
	public R updateUserEnable(@RequestBody Long[] ids) {
		return sysUserService.updateUserEnable(ids);
	}

	/**
	 * 禁用账户
	 * @param ids
	 * @return
	 */
	@SysLog("锁定账户")
	@PutMapping("/disable")
	public R updateUserDisable(@RequestBody Long[] ids) {
		return sysUserService.updateUserDisable(ids);
	}

	/**
	 * 重置密码
	 * @param user
	 * @return
	 */
	@SysLog("重置密码")
	@PutMapping("/{id}/reset")
	public R updatePswd(@PathVariable("id") Long userId, @RequestBody SysUserEntity user) {
		user.setUserId(userId);
		return sysUserService.updatePswd(user);
	}
	
}
