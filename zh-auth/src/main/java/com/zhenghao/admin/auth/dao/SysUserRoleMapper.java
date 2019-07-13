package com.zhenghao.admin.auth.dao;

import com.zhenghao.admin.auth.entity.SysUserRoleEntity;
import com.zhenghao.admin.common.dao.BaseMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户与角色关系
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2017年12月6日 上午11:13:36
 * SysUserRoleMapper.java
 */
@MapperScan
@Component
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
