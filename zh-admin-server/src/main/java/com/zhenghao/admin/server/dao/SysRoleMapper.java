package com.zhenghao.admin.server.dao;

import com.zhenghao.admin.common.dao.BaseMapper;
import com.zhenghao.admin.server.entity.SysRoleEntity;

import java.util.List;

/**
 * 系统角色
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2017年12月6日 上午11:23:20
 * SysRoleMapper.java
 */
public interface SysRoleMapper extends BaseMapper<SysRoleEntity> {

    /**
     * 根据用户id查询该用户的角色
     *
     * @param userId
     * @return
     */
    List<SysRoleEntity> listUserRoles(Long userId);
}
