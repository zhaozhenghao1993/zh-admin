package com.zhenghao.admin.auth.entity;

import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * 岗位管理
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/04/27 22:16
 * SysPostEntity.java
 */
public class SysPostEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Long id;

    /**
     * 岗位名称
     */
    @Length(min = 1, max = 30, message = "岗位名称的长度应该在1和30之间")
    private String postName;

    /**
     * 岗位编码
     */
    @Length(min = 1, max = 30, message = "岗位编码的长度应该在1和30之间")
    private String postCode;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 创建用户id
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
     * 修改时间
     */
    private Timestamp modifiedTime;


    public SysPostEntity() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    public Long getModifierId() {
        return modifierId;
    }

    public void setModifiedTime(Timestamp modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Timestamp getModifiedTime() {
        return modifiedTime;
    }

    @Override
    public String toString() {
        return "SysPostEntity{" +
                "id=" + id +
                ", postName='" + postName + '\'' +
                ", postCode='" + postCode + '\'' +
                ", orderNum=" + orderNum +
                ", creatorId=" + creatorId +
                ", createTime=" + createTime +
                ", modifierId=" + modifierId +
                ", modifiedTime=" + modifiedTime +
                '}';
    }
}