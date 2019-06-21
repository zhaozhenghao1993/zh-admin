package net.zhenghao.zh.auth.entity;

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
    private String postName;

    /**
     * 岗位编码
     */
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

}