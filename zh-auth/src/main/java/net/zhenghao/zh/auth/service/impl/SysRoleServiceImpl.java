package net.zhenghao.zh.auth.service.impl;

import com.github.pagehelper.PageHelper;
import net.zhenghao.zh.auth.dao.SysRoleMapper;
import net.zhenghao.zh.auth.dao.SysRoleMenuMapper;
import net.zhenghao.zh.auth.dao.SysUserRoleMapper;
import net.zhenghao.zh.common.entity.Page;
import net.zhenghao.zh.common.entity.Query;
import net.zhenghao.zh.common.entity.R;
import net.zhenghao.zh.common.utils.CommonUtils;
import net.zhenghao.zh.auth.entity.SysRoleEntity;
import net.zhenghao.zh.auth.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 系统角色
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年12月7日 上午10:39:00
 * SysRoleServiceImpl.java
 */
@Service("sysRoleService")
@Transactional
public class SysRoleServiceImpl implements SysRoleService {
	
	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;

	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;

	@Override
	public Page<SysRoleEntity> listRole(Map<String, Object> params) {
		Query query = new Query(params);
		Page<SysRoleEntity> page = new Page<>(query);
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		page.setData(sysRoleMapper.listForPage(query));
		return page;
	}

	@Override
	public R saveRole(SysRoleEntity role) {
		int count = sysRoleMapper.save(role);
		return CommonUtils.msg(count);
	}

	@Override
	public R getRoleById(Long id) {
		SysRoleEntity role = sysRoleMapper.getObjectById(id);
		List<Long> menuId = sysRoleMenuMapper.listMenuId(id);
		role.setMenuIdList(menuId);
		return CommonUtils.msg(role);
	}

	@Override
	public R updateRole(SysRoleEntity role) {
		int count = sysRoleMapper.update(role);
		return CommonUtils.msg(count);
	}

	@Override
	public R removeRole(Long id) {
		int count = sysRoleMapper.remove(id);
		sysUserRoleMapper.removeByRoleId(id);
		sysRoleMenuMapper.removeByRoleId(id);
		return CommonUtils.msg(count);
	}

	@Override
	public R batchRemove(Long[] id) {
		int count = sysRoleMapper.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

	@Override
	public R listRole() {
		List<SysRoleEntity> roleList = sysRoleMapper.list();
		return CommonUtils.msgNotNull(roleList);
	}

	@Override
	public R updateRoleAuthorization(SysRoleEntity role) {
		Long roleId = role.getRoleId();
		sysRoleMenuMapper.remove(roleId);
		Query query = new Query();
		query.put("roleId", roleId);
		query.put("menuIdList", role.getMenuIdList());
		int count = sysRoleMenuMapper.save(query);
		return CommonUtils.msg(count);
	}

}
