package com.zhenghao.admin.server.entity.dto;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 🙃
 * 🙃 修改当前password封装dto类
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/03/30 23:15
 * SysUserPasswordVO.java
 */

public class SysUserPasswordDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 老密码
     */
    @NotBlank(message = "原密码不能为空 !")
    private String oldPassword;

    /**
     * 新密码
     */
    @NotBlank(message = "新密码不能为空 !")
    private String password;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
