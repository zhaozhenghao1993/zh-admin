package net.zhenghao.zh.shiro.manager;

import net.zhenghao.zh.common.entity.Page;
import net.zhenghao.zh.common.entity.Query;
import net.zhenghao.zh.common.entity.SysUserEntity;

import java.util.List;
import java.util.Set;

/**
 * 系统用户
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年12月6日 下午1:42:18
 * SysUserManager.java
 */
public interface SysUserManager {

	SysUserEntity getByUserName(String username);
	
	List<SysUserEntity> listUser(Page<SysUserEntity> page, Query search);
	
	int saveUser(SysUserEntity user);
	
	SysUserEntity getById(Long userId);
	
	int updateUser(SysUserEntity user);

	int updateObject(SysUserEntity user);

	int batchRemove(Long[] id);
	
	Set<String> listUserPerms(Long userId);
	
	Set<String> listUserRoles(Long userId);
	
	int updatePswdByUser(Query query);
	
	int updateUserEnable(Long[] id);//授权
	
	int updateUserDisable(Long[] id);//取消权限
	
	int updatePswd(SysUserEntity user);
	
	SysUserEntity getUserById(Long userId);//不包含角色信息
}
