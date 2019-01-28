package net.zhenghao.zh.auth.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 菜单
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年12月5日 下午1:09:39
 * SysMenuEntity.java
 */
public class SysMenuEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 菜单ID
	 */
	private Long menuId;

	/**
	 * 父级ID,一级菜单为0
	 */
	private Long parentId;

	/**
	 * 父级菜单名称
	 */
	private String parentName;

	/**
	 * 菜单名称
	 */
	private String name;

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

	/**
	 * 类型(0:目录  1:菜单   2:按钮	3:uri)
	 */
	private Integer type;

	/**
	 * 菜单图标
	 */
	private String icon;

	/**
	 * 排序
	 */
	private Integer orderNum;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 创建人name
	 */
	private String createUser;

	/**
	 * 创建时间
	 */
	private Timestamp createTime;

	/**
	 * 最后修改人
	 */
	private String updateUser;

	/**
	 * 最后修改时间
	 */
	private Timestamp updateTime;

	public SysMenuEntity() {
		super();
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
