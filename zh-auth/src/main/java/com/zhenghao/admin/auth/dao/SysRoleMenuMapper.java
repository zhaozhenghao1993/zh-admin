package com.zhenghao.admin.auth.dao;

import com.zhenghao.admin.auth.entity.SysRoleMenuEntity;
import com.zhenghao.admin.common.dao.BaseMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统角色与菜单关系
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2017年12月6日 上午11:28:45
 * SysRoleMenuMapper.java
 */
@MapperScan
@Component
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenuEntity> {

    int removeByMenuId(Long id);

    int removeByRoleId(Long id);

    int batchRemoveByMenuId(Long[] ids);

    int batchRemoveByRoleId(Long[] ids);

    List<Long> listMenuId(Long id);
}
