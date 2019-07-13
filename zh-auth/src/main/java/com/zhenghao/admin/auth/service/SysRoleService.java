package com.zhenghao.admin.auth.service;

import com.zhenghao.admin.auth.entity.SysRoleEntity;
import com.zhenghao.admin.common.entity.Page;
import com.zhenghao.admin.common.entity.Result;

import java.util.List;
import java.util.Map;

/**
 * 系统角色
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2017年12月7日 上午10:38:08
 * SysRoleService.java
 */
public interface SysRoleService {

    Page<SysRoleEntity> listRole(Map<String, Object> params);

    Result saveRole(SysRoleEntity role);

    Result<SysRoleEntity> getRoleById(Long id);

    Result updateRole(SysRoleEntity role);

    Result removeRole(Long id);

    Result batchRemove(Long[] ids);

    Result<List<SysRoleEntity>> listRole();

    Result updateRoleAuthorization(Long id, List<Long> menuIdList);

}
