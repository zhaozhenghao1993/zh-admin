package com.zhenghao.admin.auth.entity;

import java.io.Serializable;


/**
 * 用户岗位关联表
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/05/01 12:38
 * SysUserPostEntity.java
 */
public class SysUserPostEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Long id;

    /**
     *
     */
    private Long userId;

    /**
     *
     */
    private Long postId;


    public SysUserPostEntity() {
        super();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getPostId() {
        return postId;
    }

    @Override
    public String toString() {
        return "SysUserPostEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", postId=" + postId +
                '}';
    }
}