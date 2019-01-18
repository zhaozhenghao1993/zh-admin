package net.zhenghao.zh.common.jwt;

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

    public JWTInfo(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public JWTInfo(String userId, String username) {
        this.userId = Long.valueOf(userId);
        this.username = username;
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
}
