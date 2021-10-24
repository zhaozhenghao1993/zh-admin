package com.zhenghao.admin.server.dao;

import com.zhenghao.admin.common.dao.BaseMapper;
import com.zhenghao.admin.server.entity.SysPostEntity;

import java.util.List;

/**
 * 岗位管理
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/04/27 22:16
 * SysPostMapper.java
 */
public interface SysPostMapper extends BaseMapper<SysPostEntity> {

    /**
     * 根据用户id查询该用户的岗位
     *
     * @param userId
     * @return
     */
    List<SysPostEntity> listUserPosts(Long userId);
}