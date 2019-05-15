package net.zhenghao.zh.auth.service.impl;

import com.github.pagehelper.PageHelper;
import net.zhenghao.zh.auth.dao.*;
import net.zhenghao.zh.auth.entity.SysMenuEntity;
import net.zhenghao.zh.auth.entity.SysOrgEntity;
import net.zhenghao.zh.auth.entity.SysUserEntity;
import net.zhenghao.zh.auth.handler.UserAvatarHandler;
import net.zhenghao.zh.common.constant.SystemConstant;
import net.zhenghao.zh.common.entity.Page;
import net.zhenghao.zh.common.entity.Query;
import net.zhenghao.zh.common.entity.R;
import net.zhenghao.zh.common.utils.CommonUtils;
import net.zhenghao.zh.common.utils.MD5Utils;
import net.zhenghao.zh.auth.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2019年2月17日 上午9:10:21
 * SysUserServiceImpl.java
 */
@Service("sysUserService")
@Transactional
public class SysUserServiceImpl implements SysUserService {
	
	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Autowired
	private SysPostMapper sysPostMapper;

	@Autowired
	private SysOrgMapper sysOrgMapper;

	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;

	@Autowired
	private SysUserPostMapper sysUserPostMapper;

	@Autowired
	private UserAvatarHandler userAvatarHandler;

	@Override
	public List<SysMenuEntity> listUserPerms(Long userId) {
		return sysMenuMapper.listUserPerms(userId);
	}

	@Override
	public SysUserEntity getUserByName(String username) {
		return sysUserMapper.getByUserName(username);
	}

	@Override
	public Page<SysUserEntity> listUser(Map<String, Object> params) {
		Query query = new Query(params);
		Page<SysUserEntity> page = new Page<>(query);
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		page.setData(sysUserMapper.listForPage(query));
		return page;
	}

	@Override
	public R getUserInfo(Long userId) {
		SysUserEntity user = sysUserMapper.getObjectById(userId);
		user.setRoles(sysRoleMapper.listUserRoles(userId));
		user.setPerms(sysMenuMapper.listUserMenu(userId));
		user.setPosts(sysPostMapper.listUserPosts(userId));
		// 设置组织列表
		if (user.getOrgId() != null && user.getOrgId() != 0L) {
			SysOrgEntity org = sysOrgMapper.getObjectById(user.getOrgId());
			user.setOrgs(sysOrgMapper.listByOrgIds(org.getAncestors() + ',' + user.getOrgId()));
		}
		return CommonUtils.msg(user);
	}

	@Override
	public R profileUser(SysUserEntity user, MultipartFile file) {
		user.setAvatar(userAvatarHandler.avatarHandler(user.getUserId(), file));
		int count = sysUserMapper.update(user);
		return CommonUtils.msg(count);
	}

	@Override
	public R saveUser(SysUserEntity user) {
		if (StringUtils.isBlank(user.getUsername())) {
			return R.error("The username cannot be empty !");
		}
		if (sysUserMapper.getCountByUserName(user.getUsername()) > 0) {
			return R.error("The username already exists !");
		}
		user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
		int count = sysUserMapper.save(user);
		if (user.getRoleIdList() != null && !user.getRoleIdList().isEmpty()) {
			Query query = new Query();
			query.put("userId", user.getUserId());
			query.put("roleIdList", user.getRoleIdList());
			sysUserRoleMapper.save(query);
		}
		if (user.getPostIdList() != null && !user.getPostIdList().isEmpty()) {
			Query query = new Query();
			query.put("userId", user.getUserId());
			query.put("postIdList", user.getPostIdList());
			sysUserPostMapper.save(query);
		}
		return CommonUtils.msg(count);
	}

	@Override
	public R getUserById(Long userId) {
		SysUserEntity user = sysUserMapper.getObjectById(userId);
		List<Long> roleId = sysUserRoleMapper.listUserRoleId(userId);
		List<Long> postId = sysUserPostMapper.listUserPostId(userId);
		user.setRoleIdList(roleId);
		user.setPostIdList(postId);
		return CommonUtils.msg(user);
	}

	@Override
	public R updateUser(SysUserEntity user) {
		if (StringUtils.isBlank(user.getUsername())) {
			return R.error("The username cannot be empty !");
		}
		SysUserEntity userEntity = sysUserMapper.getByUserName(user.getUsername());
		if (userEntity != null && userEntity.getUserId() != user.getUserId()) {
			return R.error("The username already exists !");
		}
		int count = sysUserMapper.update(user);
		Long userId = user.getUserId();
		sysUserRoleMapper.removeByUserId(userId);
		if (user.getRoleIdList() != null && !user.getRoleIdList().isEmpty()) {
			Query query = new Query();
			query.put("userId", userId);
			query.put("roleIdList", user.getRoleIdList());
			sysUserRoleMapper.save(query);
		}
		sysUserPostMapper.removeByUserId(userId);
		if (user.getPostIdList() != null && !user.getPostIdList().isEmpty()) {
			Query query = new Query();
			query.put("userId", userId);
			query.put("postIdList", user.getPostIdList());
			sysUserPostMapper.save(query);
		}
		return CommonUtils.msg(count);
	}

	@Override
	public R removeUser(Long id) {
		if (id == 1L) {
			return R.error("admin不能删除!");
		}
		int count = sysUserMapper.remove(id);
		sysUserRoleMapper.removeByUserId(id);
		sysUserPostMapper.removeByUserId(id);
		return CommonUtils.msg(count);
	}

	@Override
	public R batchRemove(Long[] ids) {
		if (Arrays.stream(ids).anyMatch(id -> id == 1L)) {
			return R.error("包含admin不能删除!");
		}
		int count = sysUserMapper.batchRemove(ids);
		sysUserRoleMapper.batchRemoveByUserId(ids);
		sysUserPostMapper.batchRemoveByUserId(ids);
		return CommonUtils.msg(ids, count);
	}

	@Override
	public R updatePasswordByUser(SysUserEntity user) {
		String username = user.getUsername();
		String password = user.getPassword();
		String newPassword = user.getEmail();
		password = MD5Utils.encrypt(username, password);
		newPassword = MD5Utils.encrypt(username, newPassword);
		Query query = new Query();
		query.put("userId", user.getUserId());
		query.put("password", password);
		query.put("newPassword", newPassword);
		int count = sysUserMapper.updatePasswordByUser(query);
		if(!CommonUtils.isIntThanZero(count)) {
			return R.error("原密码错误");
		}
		return CommonUtils.msg(count);
	}

	@Override
	public R updateUserEnable(Long[] ids) {
		Query query = new Query();
		query.put("ids", ids);
		query.put("status", SystemConstant.StatusType.ENABLE.getValue());
		int count = sysUserMapper.updateUserStatus(query);
		return CommonUtils.msg(ids, count);
	}

	@Override
	public R updateUserDisable(Long[] ids) {
		Query query = new Query();
		query.put("ids", ids);
		query.put("status", SystemConstant.StatusType.DISABLE.getValue());
		int count = sysUserMapper.updateUserStatus(query);
		return CommonUtils.msg(ids, count);
	}

	@Override
	public R updatePassword(SysUserEntity user) {
		SysUserEntity currUser = sysUserMapper.getObjectById(user.getUserId());
		user.setPassword(MD5Utils.encrypt(currUser.getUsername(), user.getPassword()));
		int count = sysUserMapper.updatePassword(user);
		return CommonUtils.msg(count);
	}

	@Override
	public R updateThemeByUserId(SysUserEntity user) {
		int count = sysUserMapper.updateThemeByUserId(user);
		return CommonUtils.msg(count);
	}

	@Override
	public R updateColorByUserId(SysUserEntity user) {
		int count = sysUserMapper.updateColorByUserId(user);
		return CommonUtils.msg(count);
	}

}
