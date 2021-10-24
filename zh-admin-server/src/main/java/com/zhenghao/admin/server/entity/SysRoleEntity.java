package com.zhenghao.admin.server.entity;

import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 角色
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2017年12月5日 下午1:19:28
 * SysRoleEntity.java
 */
public class SysRoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private Long id;

    /**
     * 角色名称
     */
    @Length(min = 1, max = 30, message = "角色名称的长度应该在1和30之间")
    private String roleName;

    /**
     * 角色标识
     */
    @Length(min = 1, max = 30, message = "角色标识的长度应该在1和30之间")
    private String roleSign;

    /**
     * 备注
     */
    @Length(max = 100, message = "备注的长度应该在100以内")
    private String remark;

    /**
     * 创建人id
     */
    private Long creatorId;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 最后修改人id
     */
    private Long modifierId;

    /**
     * 最后修改时间
     */
    private Timestamp modifiedTime;

    private List<Long> menuIdList;

    public SysRoleEntity() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleSign() {
        return roleSign;
    }

    public void setRoleSign(String roleSign) {
        this.roleSign = roleSign;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getModifierId() {
        return modifierId;
    }

    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    public Timestamp getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Timestamp modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public List<Long> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<Long> menuIdList) {
        this.menuIdList = menuIdList;
    }

    @Override
    public String toString() {
        return "SysRoleEntity{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleSign='" + roleSign + '\'' +
                ", remark='" + remark + '\'' +
                ", creatorId=" + creatorId +
                ", createTime=" + createTime +
                ", modifierId=" + modifierId +
                ", modifiedTime=" + modifiedTime +
                ", menuIdList=" + menuIdList +
                '}';
    }
}
