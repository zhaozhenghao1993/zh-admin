package com.zhenghao.admin.server.entity;

import com.zhenghao.admin.common.entity.TreeNode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 菜单
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2017年12月5日 下午1:09:39
 * SysMenuEntity.java
 */
public class SysMenuEntity extends TreeNode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 父级菜单名称
     */
    private String parentName;

    /**
     * 菜单名称
     */
    @Length(min = 1, max = 30, message = "菜单名称的长度应该在1和30之间")
    @NotBlank(message = "菜单名称不能为空")
    private String name;

    /**
     * 菜单uri
     */
    @Length(max = 200, message = "菜单uri的长度应该小于200")
    private String uri;

    /**
     * 请求method
     */
    private String method;

    /**
     * 授权标识(多个用逗号分隔,如:user:list,user:create)
     */
    @Length(min = 1, max = 200, message = "授权标识的长度应该在1和200之间")
    private String perms;

    /**
     * 类型(0:目录  1:菜单   2:按钮	3:uri)
     */
    @Range(min = 0, max = 3, message = "菜单类型值不正确")
    @NotNull(message = "菜单类型不能为空")
    private Integer type;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 描述
     */
    @Length(max = 300, message = "备注的长度应该在300以内")
    private String description;

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

    public SysMenuEntity() {
        super();
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

    @Override
    public String toString() {
        return super.toString() +
                "SysMenuEntity{" +
                "parentName='" + parentName + '\'' +
                ", name='" + name + '\'' +
                ", uri='" + uri + '\'' +
                ", method='" + method + '\'' +
                ", perms='" + perms + '\'' +
                ", type=" + type +
                ", orderNum=" + orderNum +
                ", description='" + description + '\'' +
                ", creatorId=" + creatorId +
                ", createTime=" + createTime +
                ", modifierId=" + modifierId +
                ", modifiedTime=" + modifiedTime +
                '}';
    }
}
