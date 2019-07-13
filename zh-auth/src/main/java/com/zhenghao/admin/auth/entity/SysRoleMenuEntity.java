package com.zhenghao.admin.auth.entity;

import java.io.Serializable;

/**
 * 角色与菜单的对应关系
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2017年12月5日 下午1:37:19
 * SysRoleMenuEntity.java
 */
public class SysRoleMenuEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录id
     */
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

    public SysRoleMenuEntity() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "SysRoleMenuEntity{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", menuId=" + menuId +
                '}';
    }
}
