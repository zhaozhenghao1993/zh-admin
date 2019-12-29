package com.zhenghao.admin.common.util;

import com.zhenghao.admin.common.jwt.JWTInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 🙃
 * 🙃 根据token获取JWTInfo
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/01/27 21:57
 * UserAuthUtils.java
 */
@Component
public class UserAuthUtils {

    private final JWTTokenUtils jwtTokenUtils;

    @Autowired
    public UserAuthUtils(JWTTokenUtils jwtTokenUtils) {
        this.jwtTokenUtils = jwtTokenUtils;
    }

    public JWTInfo getInfoFromToken(String token) {
        return jwtTokenUtils.getInfoFromToken(token);
    }

    public String getTokenFromJWTInfo(JWTInfo jwtInfo) {
        return jwtTokenUtils.generateToken(jwtInfo);
    }
}
