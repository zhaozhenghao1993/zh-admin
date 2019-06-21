package net.zhenghao.zh.auth.service;

import net.zhenghao.zh.auth.entity.SysOrgEntity;
import net.zhenghao.zh.common.entity.Page;
import net.zhenghao.zh.common.entity.Result;
import net.zhenghao.zh.common.vo.TreeVO;

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
