package com.zhenghao.admin.server.entity.vo;

import java.io.Serializable;

/**
 * 🙃
 * 🙃 登陆响应VO类
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/05/18 23:09
 * SysLoginVO.java
 */

public class SysLoginVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String token;

    public SysLoginVO() {
    }

    public SysLoginVO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
