package net.zhenghao.zh.shiro.controller;

import net.zhenghao.zh.common.annotation.SysLog;
import net.zhenghao.zh.common.constant.SystemConstant;
import net.zhenghao.zh.common.controller.AbstractController;
import net.zhenghao.zh.common.entity.Page;
import net.zhenghao.zh.common.entity.R;
import net.zhenghao.zh.shiro.entity.SysRoleEntity;
import net.zhenghao.zh.shiro.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 系统角色controller
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年12月25日 下午2:48:50
 * SysRoleController.java
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {

	@Autowired
	private SysRoleService sysRoleService;
	
	@RequestMapping("/list")
	public Page<SysRoleEntity> list(@RequestBody Map<String, Object> params) {
		if (getUserId() != SystemConstant.SUPER_ADMIN) {
			//如果不是admin,则只能查看自己创建的角色
			params.put("userIdCreate", getUserId());
		}
		return sysRoleService.listRole(params);
	}
	
	/**
	 * 用户角色
	 * @return
	 */
	@RequestMapping("/select")
	public R listRole() {
		return sysRoleService.listRole();
	}
	
	/**
	 * 新增角色
	 * @param role
	 * @return
	 */
	@SysLog("新增角色")
	@RequestMapping("/save")
	public R saveRole(@RequestBody SysRoleEntity role) {
		role.setUserIdCreate(getUserId());
		return sysRoleService.saveRole(role);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public R getRoleById(@RequestBody Long id) {
		return sysRoleService.getRoleById(id);
	}
	
	/**
	 * 修改角色
	 * @param role
	 * @return
	 */
	@SysLog("修改角色")
	@RequestMapping("/update")
	public R updateRole(@RequestBody SysRoleEntity role) {
		return sysRoleService.updateRole(role);
	}
	
	/**
	 * 批量删除
	 * @param id
	 * @return
	 */
	@SysLog("删除角色")
	@RequestMapping("/remove")
	public R batchRemove(@RequestBody Long[] id) {
		return sysRoleService.batchRemove(id);
	}
	
	/**
	 * 分配权限
	 * @param role
	 * @return
	 */
	@SysLog(value = "分配权限", type = "AUTHORIZATION")
	@RequestMapping("/authorize")
	public R updateRoleAuthorization(@RequestBody SysRoleEntity role) {
		return sysRoleService.updateRoleAuthorization(role);
	}
}
