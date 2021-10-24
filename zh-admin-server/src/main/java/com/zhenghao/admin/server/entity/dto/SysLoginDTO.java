package com.zhenghao.admin.server.entity.dto;

import lombok.Data;

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
@Data
public class SysLoginDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private String uuid;

    private String captcha;
}
