package com.zhenghao.admin.auth.entity;

import com.zhenghao.admin.common.entity.TreeNode;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 组织管理
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2017年12月5日 下午1:09:39
 * SysMenuEntity.java
 */
public class SysOrgEntity extends TreeNode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 组织名称
     */
    @Length(min = 1, max = 30, message = "组织名称的长度应该在1和30之间")
    private String orgName;

    /**
     * 排序
     */
    private Integer orderNum;

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

    public SysOrgEntity() {
        super();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
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
                "SysOrgEntity{" +
                ", orgName='" + orgName + '\'' +
                ", orderNum=" + orderNum +
                ", creatorId=" + creatorId +
                ", createTime=" + createTime +
                ", modifierId=" + modifierId +
                ", modifiedTime=" + modifiedTime +
                '}';
    }
}
