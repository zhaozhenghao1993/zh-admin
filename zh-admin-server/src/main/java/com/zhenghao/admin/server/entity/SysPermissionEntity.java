package com.zhenghao.admin.server.entity;

/**
 * 🙃
 * 🙃 权限实体类
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/01/19 22:33
 * SysPermissionEntity.java
 */

public class SysPermissionEntity {

    public SysPermissionEntity() {
    }

    public SysPermissionEntity(String uri, String method) {
        this.uri = uri;
        this.method = method;
    }

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 类型(0:目录  1:菜单   2:按钮)
     */
    private Integer type;

    /**
     * 菜单uri
     */
    private String uri;

    /**
     * 请求method
     */
    private String method;

    /**
     * 授权标识(多个用逗号分隔,如:user:list,user:create)
     */
    private String perms;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    @Override
    public String toString() {
        return "SysPermissionEntity{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", uri='" + uri + '\'' +
                ", method='" + method + '\'' +
                ", perms='" + perms + '\'' +
                '}';
    }
}
