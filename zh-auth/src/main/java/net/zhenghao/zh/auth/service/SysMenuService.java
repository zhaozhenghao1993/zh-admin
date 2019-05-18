package net.zhenghao.zh.auth.service;

import net.zhenghao.zh.common.entity.Page;
import net.zhenghao.zh.auth.entity.SysMenuEntity;
import net.zhenghao.zh.common.entity.Result;
import net.zhenghao.zh.common.vo.TreeVO;

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
	
	Result<List<TreeVO>> listTree(Map<String, Object> params);

	Result saveMenu(SysMenuEntity menu);

	Result<SysMenuEntity> getMenuById(Long id);

	Result updateMenu(SysMenuEntity menu);

	Result remove(Long id);

	Result batchRemove(Long[] ids);
	
}
