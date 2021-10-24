package com.zhenghao.admin.server.dao;

import com.zhenghao.admin.common.dao.BaseMapper;
import com.zhenghao.admin.server.entity.SysRoleMenuEntity;

import java.util.List;

/**
 * 系统角色与菜单关系
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2017年12月6日 上午11:28:45
 * SysRoleMenuMapper.java
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenuEntity> {

    int removeByMenuId(Long id);

    int removeByRoleId(Long id);

    int batchRemoveByMenuId(Long[] ids);

    int batchRemoveByRoleId(Long[] ids);

    List<Long> listMenuId(Long id);
}
