package com.zhenghao.admin.common.jwt;

/**
 * 🙃
 * 🙃 JWT信息实体类
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/01/14 22:41
 * JWTInfo.java
 */

public class JWTInfo {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户姓名
     */
    private String name;

    public JWTInfo(Long userId, String username, String name) {
        this.userId = userId;
        this.username = username;
        this.name = name;
    }

    public JWTInfo(String userId, String username, String name) {
        this.userId = Long.valueOf(userId);
        this.username = username;
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
