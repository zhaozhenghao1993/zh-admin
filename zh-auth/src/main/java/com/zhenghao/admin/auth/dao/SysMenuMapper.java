package com.zhenghao.admin.auth.dao;

import com.zhenghao.admin.auth.entity.SysMenuEntity;
import com.zhenghao.admin.common.dao.BaseMapper;
import com.zhenghao.admin.common.vo.TreeVO;

import java.util.List;

/**
 * 系统菜单dao
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2017年12月6日 下午1:28:55
 * SysMenuMapper.java
 */
public interface SysMenuMapper extends BaseMapper<SysMenuEntity> {

    /**
     * 得到Tree列表(不含button)
     *
     * @return
     */
    List<TreeVO> listTreeNotButton();

    /**
     * 得到Tree列表
     *
     * @return
     */
    List<TreeVO> listTree();

    /**
     * 根据menuId获取子菜单的数量
     *
     * @return
     */
    int countChildById(Long id);

    /**
     * 得到该用户所有URI权限
     *
     * @param userId
     * @return
     */
    List<SysMenuEntity> listUserPerms(Long userId);

    /**
     * 得到该用户所有菜单
     *
     * @param userId
     * @return
     */
    List<SysMenuEntity> listUserMenu(Long userId);

}
