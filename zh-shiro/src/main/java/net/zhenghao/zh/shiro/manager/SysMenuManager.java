package net.zhenghao.zh.shiro.manager;

import net.zhenghao.zh.common.entity.Query;
import net.zhenghao.zh.shiro.entity.SysMenuEntity;

import java.util.List;

/**
 * 系统菜单
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年12月6日 下午3:41:41
 * SysMenuManager.java
 */

public interface SysMenuManager {

	List<SysMenuEntity> listUserMenu(Long userId);
	
	List<SysMenuEntity> listMenu(Query search);
	
	List<SysMenuEntity> listNotButton();
	
	int saveMenu(SysMenuEntity menu);
	
	SysMenuEntity getMenuById(Long id);
	
	int updateMenu(SysMenuEntity menu);
	
	int batchRemove(Long[] id);
}
