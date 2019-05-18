package net.zhenghao.zh.auth.service;

import net.zhenghao.zh.auth.entity.SysMenuEntity;
import net.zhenghao.zh.auth.entity.SysUserEntity;
import net.zhenghao.zh.common.entity.Page;
import net.zhenghao.zh.common.entity.Result;
import org.springframework.web.multipart.MultipartFile;

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

	Result<SysUserEntity> getUserInfo(Long userId);

	Result profileUser(SysUserEntity user, MultipartFile file);

	Result saveUser(SysUserEntity user);

	Result<SysUserEntity> getUserById(Long userId);

	Result updateUser(SysUserEntity user);

	Result removeUser(Long id);

	Result batchRemove(Long[] ids);

	Result updatePasswordByUser(SysUserEntity user);

	Result updateUserEnable(Long[] ids);

	Result updateUserDisable(Long[] ids);

	Result updatePassword(SysUserEntity user);

	Result updateThemeByUserId(SysUserEntity user);

	Result updateColorByUserId(SysUserEntity user);

}
