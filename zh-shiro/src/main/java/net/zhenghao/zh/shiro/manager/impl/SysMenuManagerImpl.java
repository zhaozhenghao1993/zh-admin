package net.zhenghao.zh.shiro.manager.impl;

import net.zhenghao.zh.common.entity.Query;
import net.zhenghao.zh.shiro.dao.SysMenuMapper;
import net.zhenghao.zh.shiro.dao.SysRoleMenuMapper;
import net.zhenghao.zh.shiro.entity.SysMenuEntity;
import net.zhenghao.zh.shiro.manager.SysMenuManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统菜单
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年12月6日 下午3:45:23
 * SysMenuManagerImpl.java
 */
@Component("sysMenuManager")
public class SysMenuManagerImpl implements SysMenuManager {
	
	@Autowired
	private SysMenuMapper sysMenuMapper;
	
	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;

	/**
	 * 查询该用户所能得到的菜单
	 * @param userId
	 * @return
	 */
	@Override
	public List<SysMenuEntity> listUserMenu(Long userId) {
		return sysMenuMapper.listUserMenu(userId);
	}

	@Override
	public List<SysMenuEntity> listMenu(Query search) {
		return sysMenuMapper.list(search);
	}

	@Override
	public List<SysMenuEntity> listNotButton() {
		return sysMenuMapper.listNotButton();
	}
	
	@Override
	public int saveMenu(SysMenuEntity menu) {
		return sysMenuMapper.save(menu);
	}

	@Override
	public SysMenuEntity getMenuById(Long id) {
		return sysMenuMapper.getObjectById(id);
	}

	@Override
	public int updateMenu(SysMenuEntity menu) {
		return sysMenuMapper.update(menu);
	}

	@Override
	public int batchRemove(Long[] id) {
		int count = sysMenuMapper.batchRemove(id);
		sysRoleMenuMapper.batchRemoveByMenuId(id);
		return count;
	}

}
