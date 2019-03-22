package net.zhenghao.zh.auth.service;

import net.zhenghao.zh.common.entity.Page;
import net.zhenghao.zh.common.entity.R;
import net.zhenghao.zh.auth.entity.SysMenuEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统菜单
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2019年1月29日 上午10:43:52
 * SysMenuService.java
 */
public interface SysMenuService {
	
	Page<SysMenuEntity> listMenu(Map<String, Object> params);
	
	R listNotButton();
	
	R saveMenu(SysMenuEntity menu);

	R getMenuById(Long id);
	
	R updateMenu(SysMenuEntity menu);

	R remove(Long id);
	
	R batchRemove(Long[] id);
	
}
