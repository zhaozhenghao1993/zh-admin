package net.zhenghao.zh.auth.service.impl;

import net.zhenghao.zh.auth.dao.SysMenuMapper;
import net.zhenghao.zh.auth.dao.SysRoleMenuMapper;
import net.zhenghao.zh.auth.vo.SysMenuVO;
import net.zhenghao.zh.common.entity.Page;
import net.zhenghao.zh.common.entity.Query;
import net.zhenghao.zh.common.entity.R;
import net.zhenghao.zh.common.utils.CommonUtils;
import net.zhenghao.zh.auth.entity.SysMenuEntity;
import net.zhenghao.zh.auth.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 系统菜单
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年12月7日 下午2:51:12
 * SysMenuServiceImpl.java
 */
@Service("sysMenuService")
@Transactional
public class SysMenuServiceImpl implements SysMenuService {
	
	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;

	@Override
	public Page<SysMenuEntity> listMenu(Map<String, Object> params) {
		Query query = new Query(params);
		Page<SysMenuEntity> page = new Page<>(query);
		page.setData(sysMenuMapper.list(query));
		return page;
	}

	@Override
	public R listTree(Map<String, Object> params) {
		List<SysMenuVO> listRoot = new ArrayList<>();
		if (params.get("isNotButton") != null && "true".equals(params.get("isNotButton"))) {
			List<SysMenuVO> menuList = sysMenuMapper.listTreeNotButton();
			SysMenuVO root = new SysMenuVO();
			root.setKey("0");
			root.setTitle("主目录");
			root.setValue("0");
			root.setParentId("-1");
			root.setChildren(menuList);
			listRoot.add(root);
		} else {
			listRoot = sysMenuMapper.listTree();
		}
		return CommonUtils.msgNotNull(listRoot);
	}

	@Override
	public R saveMenu(SysMenuEntity menu) {
		int count = sysMenuMapper.save(menu);
		return CommonUtils.msg(count);
	}

	@Override
	public R getMenuById(Long id) {
		SysMenuEntity menu = sysMenuMapper.getObjectById(id);
		return CommonUtils.msg(menu);
	}

	@Override
	public R updateMenu(SysMenuEntity menu) {
		int count = sysMenuMapper.update(menu);
		return CommonUtils.msg(count);
	}

	@Override
	public R remove(Long id) {
		int count = sysMenuMapper.remove(id);
		sysRoleMenuMapper.removeByMenuId(id);
		return CommonUtils.msg(count);
	}

	@Override
	public R batchRemove(Long[] ids) {
		int count = sysMenuMapper.batchRemove(ids);
		sysRoleMenuMapper.batchRemoveByMenuId(ids);
		return CommonUtils.msg(ids, count);
	}

}
