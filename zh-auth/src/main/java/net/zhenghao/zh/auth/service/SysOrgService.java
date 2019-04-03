package net.zhenghao.zh.auth.service;

import net.zhenghao.zh.auth.entity.SysOrgEntity;
import net.zhenghao.zh.common.entity.Page;
import net.zhenghao.zh.common.entity.R;

import java.util.Map;

/**
 * 组织管理
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2019年1月29日 上午10:43:52
 * SysMenuService.java
 */
public interface SysOrgService {
	
	Page<SysOrgEntity> listOrg(Map<String, Object> params);
	
	R listTree(Map<String, Object> params);
	
	R saveOrg(SysOrgEntity org);

	R getOrgById(Long id);
	
	R updateOrg(SysOrgEntity org);

	R remove(Long id);
	
}
