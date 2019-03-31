package net.zhenghao.zh.auth.service;

import net.zhenghao.zh.auth.entity.SysMenuEntity;
import net.zhenghao.zh.auth.entity.SysUserEntity;
import net.zhenghao.zh.common.entity.Page;
import net.zhenghao.zh.common.entity.R;

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

	R getUserInfo(Long userId);

	R profileUser(SysUserEntity user);
	
	R saveUser(SysUserEntity user);
	
	R getUserById(Long userId);
	
	R updateUser(SysUserEntity user);

	R removeUser(Long id);
	
	R batchRemove(Long[] id);

	R listUserMenu(Long userId);

	R updatePasswordByUser(SysUserEntity user);
	
	R updateUserEnable(Long[] id);
	
	R updateUserDisable(Long[] id);
	
	R updatePassword(SysUserEntity user);

	R updateThemeByUserId(SysUserEntity user);

	R updateColorByUserId(SysUserEntity user);

}
