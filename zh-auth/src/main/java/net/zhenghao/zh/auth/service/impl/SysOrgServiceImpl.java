package net.zhenghao.zh.auth.service.impl;

import net.zhenghao.zh.auth.dao.SysOrgMapper;
import net.zhenghao.zh.auth.entity.SysOrgEntity;
import net.zhenghao.zh.auth.service.SysOrgService;
import net.zhenghao.zh.auth.vo.SysTreeVO;
import net.zhenghao.zh.common.entity.Page;
import net.zhenghao.zh.common.entity.Query;
import net.zhenghao.zh.common.entity.R;
import net.zhenghao.zh.common.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 系统组织
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年12月7日 下午2:51:12
 * SysOrgServiceImpl.java
 */
@Service("sysOrgService")
@Transactional
public class SysOrgServiceImpl implements SysOrgService {
	
	@Autowired
	private SysOrgMapper sysOrgMapper;

	@Override
	public Page<SysOrgEntity> listOrg(Map<String, Object> params) {
		Query query = new Query(params);
		Page<SysOrgEntity> page = new Page<>(query);
		page.setData(sysOrgMapper.list(query));
		return page;
	}

	@Override
	public R listTree() {
		List<SysTreeVO> list = sysOrgMapper.listTree();
		return CommonUtils.msgNotNull(list);
	}

	@Override
	public R saveOrg(SysOrgEntity org) {
		SysOrgEntity orgParent = sysOrgMapper.getObjectById(org.getParentId());
		if (orgParent != null) {
			String ancestors = orgParent.getAncestors() + "," + orgParent.getOrgId();
			org.setAncestors(ancestors);
		} else {
			org.setAncestors(orgParent.getOrgId() + "");
		}
		int count = sysOrgMapper.save(org);
		return CommonUtils.msg(count);
	}

	@Override
	public R getOrgById(Long id) {
		SysOrgEntity org = sysOrgMapper.getObjectById(id);
		return CommonUtils.msg(org);
	}

	@Override
	public R updateOrg(SysOrgEntity org) {
		SysOrgEntity orgParent = sysOrgMapper.getObjectById(org.getParentId());
		if (orgParent != null) {
			String ancestors = orgParent.getAncestors() + "," + orgParent.getOrgId();
			org.setAncestors(ancestors);
		} else {
			org.setAncestors(orgParent.getOrgId() + "");
		}
		int count = sysOrgMapper.update(org);
		return CommonUtils.msg(count);
	}

	@Override
	public R remove(Long id) {
		int childCount = sysOrgMapper.getChildCountByOrgId(id);
		if (childCount > 0) {
			return R.error("该组织含有子组织,请先删除子组织!");
		}
		int count = sysOrgMapper.remove(id);
		return CommonUtils.msg(count);
	}

}
