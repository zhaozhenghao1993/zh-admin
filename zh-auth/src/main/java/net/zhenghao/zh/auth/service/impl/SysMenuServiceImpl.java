package net.zhenghao.zh.auth.service.impl;

import net.zhenghao.zh.auth.dao.SysMenuMapper;
import net.zhenghao.zh.auth.dao.SysRoleMenuMapper;
import net.zhenghao.zh.common.constant.SystemConstant;
import net.zhenghao.zh.common.entity.Result;
import net.zhenghao.zh.common.utils.TreeUtils;
import net.zhenghao.zh.common.vo.TreeVO;
import net.zhenghao.zh.common.entity.Page;
import net.zhenghao.zh.common.entity.Query;
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
		page.setData(TreeUtils.build(sysMenuMapper.list(query), SystemConstant.TREE_ROOT));
		return page;
	}

	@Override
	public Result<List<TreeVO>> listTree(Map<String, Object> params) {
		List<TreeVO> listRoot = new ArrayList<>();
		if (params.get("isNotButton") != null && "true".equals(params.get("isNotButton"))) {
			List<TreeVO> treeList = sysMenuMapper.listTreeNotButton();
			TreeVO root = new TreeVO();
			root.setId(0L);
			root.setKey(0L);
			root.setTitle("主目录");
			root.setValue("0");
			root.setParentId(-1L);
			treeList.add(root);
			// root.setChildren(menuList);
			listRoot.add(TreeUtils.build(treeList, -1L));
		} else {
			listRoot = sysMenuMapper.listTree();
		}
		return CommonUtils.msgNotNull(listRoot);
	}

	@Override
	public Result saveMenu(SysMenuEntity menu) {
		int count = sysMenuMapper.save(menu);
		return CommonUtils.msg(count);
	}

	@Override
	public Result<SysMenuEntity> getMenuById(Long id) {
		SysMenuEntity menu = sysMenuMapper.getObjectById(id);
		return CommonUtils.msg(menu);
	}

	@Override
	public Result updateMenu(SysMenuEntity menu) {
		int count = sysMenuMapper.update(menu);
		return CommonUtils.msg(count);
	}

	@Override
	public Result remove(Long id) {
		int childCount = sysMenuMapper.getChildCountByMenuId(id);
		if (childCount > 0) {
			return Result.ofFail("该菜单含有子菜单,请先删除子菜单!");
		}
		int count = sysMenuMapper.remove(id);
		sysRoleMenuMapper.removeByMenuId(id);
		return CommonUtils.msg(count);
	}

	@Override
	public Result batchRemove(Long[] ids) {
		int count = sysMenuMapper.batchRemove(ids);
		sysRoleMenuMapper.batchRemoveByMenuId(ids);
		return CommonUtils.msg(ids, count);
	}

}
