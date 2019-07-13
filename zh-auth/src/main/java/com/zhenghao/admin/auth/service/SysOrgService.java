package com.zhenghao.admin.auth.service;

import com.zhenghao.admin.auth.entity.SysOrgEntity;
import com.zhenghao.admin.common.entity.Page;
import com.zhenghao.admin.common.entity.Result;
import com.zhenghao.admin.common.vo.TreeVO;

import java.util.List;
import java.util.Map;

/**
 * 组织管理
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019年1月29日 上午10:43:52
 * SysMenuService.java
 */
public interface SysOrgService {

    Page<SysOrgEntity> listOrg(Map<String, Object> params);

    Result<List<TreeVO>> listTree(Map<String, Object> params);

    Result saveOrg(SysOrgEntity org);

    Result<SysOrgEntity> getOrgById(Long id);

    Result updateOrg(SysOrgEntity org);

    Result remove(Long id);

}
