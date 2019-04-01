package net.zhenghao.zh.auth.service;

import net.zhenghao.zh.common.entity.Page;
import net.zhenghao.zh.common.entity.R;
import net.zhenghao.zh.auth.entity.SysRoleEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统角色
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年12月7日 上午10:38:08
 * SysRoleService.java
 */
public interface SysRoleService {

	Page<SysRoleEntity> listRole(Map<String, Object> params);
	
	R saveRole(SysRoleEntity role);
	
	R getRoleById(Long id);
	
	R updateRole(SysRoleEntity role);

	R removeRole(Long id);
	
	R batchRemove(Long[] ids);
	
	R listRole();
	
	R updateRoleAuthorization(Long roleId, List<Long> menuIdList);
	
}
