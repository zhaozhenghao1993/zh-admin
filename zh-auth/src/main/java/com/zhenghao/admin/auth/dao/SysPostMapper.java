package com.zhenghao.admin.auth.dao;

import com.zhenghao.admin.auth.entity.SysPostEntity;
import com.zhenghao.admin.common.dao.BaseMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 岗位管理
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/04/27 22:16
 * SysPostMapper.java
 */
@MapperScan
@Component
public interface SysPostMapper extends BaseMapper<SysPostEntity> {

    /**
     * 根据用户id查询该用户的岗位
     *
     * @param userId
     * @return
     */
    List<SysPostEntity> listUserPosts(Long userId);
}