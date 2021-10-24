package com.zhenghao.admin.server.dao;

import com.zhenghao.admin.common.dao.BaseMapper;
import com.zhenghao.admin.server.entity.SysUserRoleEntity;

import java.util.List;

/**
 * 用户与角色关系
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2017年12月6日 上午11:13:36
 * SysUserRoleMapper.java
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRoleEntity> {

    /**
     * 用户id下所有角色id
     *
     * @param userId
     * @return
     */
    List<Long> listUserRoleId(Long userId);

    int removeByUserId(Long id);

    int removeByRoleId(Long id);

    int batchRemoveByUserId(Long[] ids);

    int batchRemoveByRoleId(Long[] ids);

}
