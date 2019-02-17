package net.zhenghao.zh.auth.service;

import net.zhenghao.zh.auth.entity.SysMenuEntity;
import net.zhenghao.zh.common.entity.Page;
import net.zhenghao.zh.common.entity.R;
import net.zhenghao.zh.common.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年12月7日 上午9:07:06
 * SysUserService.java
 */
public interface SysUserService {

	/**
	 * 获取用户的权限filter chain
	 * @param userId
	 * @return
	 */
	List<SysMenuEntity> listUserPerms(Long userId);

	SysUserEntity getUserByName(String username);

	Page<SysUserEntity> listUser(Map<String, Object> params);
	
	R saveUser(SysUserEntity user);
	
	R getUserById(Long userId);
	
	R updateUser(SysUserEntity user);

	R removeUser(Long id);
	
	R batchRemove(Long[] id);
	
	R listUserButton(Long userId);

	R listUserMenu(Long userId);

	R updatePswdByUser(SysUserEntity user);
	
	R updateUserEnable(Long[] id);
	
	R updateUserDisable(Long[] id);
	
	R updatePswd(SysUserEntity user);
	
}
