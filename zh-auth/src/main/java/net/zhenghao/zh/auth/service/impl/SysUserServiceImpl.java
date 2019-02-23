package net.zhenghao.zh.auth.service.impl;

import com.github.pagehelper.PageHelper;
import net.zhenghao.zh.auth.dao.SysMenuMapper;
import net.zhenghao.zh.auth.dao.SysRoleMapper;
import net.zhenghao.zh.auth.dao.SysUserMapper;
import net.zhenghao.zh.auth.dao.SysUserRoleMapper;
import net.zhenghao.zh.auth.entity.SysMenuEntity;
import net.zhenghao.zh.auth.entity.SysUserEntity;
import net.zhenghao.zh.common.entity.Page;
import net.zhenghao.zh.common.entity.Query;
import net.zhenghao.zh.common.entity.R;
import net.zhenghao.zh.common.utils.CommonUtils;
import net.zhenghao.zh.common.utils.MD5Utils;
import net.zhenghao.zh.auth.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class SysUserServiceImpl implements SysUserService {
	
	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;

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
		return CommonUtils.msg(user);
	}

	@Override
	public R saveUser(SysUserEntity user) {
		user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
		int count = sysUserMapper.save(user);
		Query query = new Query();
		query.put("userId", user.getUserId());
		query.put("roleIdList", user.getRoleIdList());
		sysUserRoleMapper.save(query);
		return CommonUtils.msg(count);
	}

	@Override
	public R getUserById(Long userId) {
		SysUserEntity user = sysUserMapper.getObjectById(userId);
		List<Long> roleId = sysUserRoleMapper.listUserRoleId(userId);
		user.setRoleIdList(roleId);
		return CommonUtils.msg(user);
	}

	@Override
	public R updateUser(SysUserEntity user) {
		user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
		int count = sysUserMapper.update(user);
		Long userId = user.getUserId();
		sysUserRoleMapper.remove(userId);
		Query query = new Query();
		query.put("userId", userId);
		query.put("roleIdList", user.getRoleIdList());
		sysUserRoleMapper.save(query);
		return CommonUtils.msg(count);
	}

	@Override
	public R removeUser(Long id) {
		int count = sysUserMapper.remove(id);
		sysUserRoleMapper.removeByUserId(id);
		return CommonUtils.msg(count);
	}

	@Override
	public R batchRemove(Long[] id) {
		int count = sysUserMapper.batchRemove(id);
		sysUserRoleMapper.batchRemoveByUserId(id);
		return CommonUtils.msg(id, count);
	}

	@Override
	public R listUserButton(Long userId) {
		List<String> buttons = sysMenuMapper.listUserButton(userId);
		return CommonUtils.msgNotNull(buttons);
	}

	@Override
	public R listUserMenu(Long userId) {
		List<SysMenuEntity> menus = sysMenuMapper.listUserMenu(userId);
		return CommonUtils.msgNotNull(menus);
	}

	@Override
	public R updatePswdByUser(SysUserEntity user) {
		String username = user.getUsername();
		String pswd = user.getPassword();
		String newPswd = user.getEmail();
		pswd = MD5Utils.encrypt(username, pswd);
		newPswd = MD5Utils.encrypt(username, newPswd);
		Query query = new Query();
		query.put("userId", user.getUserId());
		query.put("pswd", pswd);
		query.put("newPswd", newPswd);
		int count = sysUserMapper.updatePswdByUser(query);
		if(!CommonUtils.isIntThanZero(count)) {
			return R.error("原密码错误");
		}
		return CommonUtils.msg(count);
	}

	@Override
	public R updateUserEnable(Long[] id) {
		//int count = sysUserMapper.updateUserEnable(id);
		//return CommonUtils.msg(id, count);
		return R.ok();
	}

	@Override
	public R updateUserDisable(Long[] id) {
		// int count = sysUserMapper.updateUserDisable(id);
		// return CommonUtils.msg(id, count);
		return R.ok();
	}

	@Override
	public R updatePswd(SysUserEntity user) {
		SysUserEntity currUser = sysUserMapper.getObjectById(user.getUserId());
		user.setPassword(MD5Utils.encrypt(currUser.getUsername(), user.getPassword()));
		int count = sysUserMapper.updatePswd(user);
		return CommonUtils.msg(count);
	}

}
