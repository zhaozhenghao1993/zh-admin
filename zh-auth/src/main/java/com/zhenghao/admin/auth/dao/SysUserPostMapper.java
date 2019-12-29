package com.zhenghao.admin.auth.dao;

import com.zhenghao.admin.auth.entity.SysUserPostEntity;
import com.zhenghao.admin.common.dao.BaseMapper;

import java.util.List;

/**
 * 用户岗位关联表
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/05/01 12:38
 * SysUserPostMapper.java
 */
public interface SysUserPostMapper extends BaseMapper<SysUserPostEntity> {

    /**
     * 用户id下所有角色id
     *
     * @param userId
     * @return
     */
    List<Long> listUserPostId(Long userId);

    int removeByUserId(Long id);

    int removeByPostId(Long id);

    int batchRemoveByUserId(Long[] ids);

    int batchRemoveByPostId(Long[] ids);
}