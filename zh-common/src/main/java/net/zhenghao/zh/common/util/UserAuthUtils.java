package net.zhenghao.zh.common.util;

import net.zhenghao.zh.common.jwt.JWTInfo;
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

    @Autowired
    private JWTTokenUtils jwtTokenUtils;

    public JWTInfo getInfoFromToken(String token) {
        return jwtTokenUtils.getInfoFromToken(token);
    }

    public String getTokenFromJWTInfo(JWTInfo jwtInfo) {
        return jwtTokenUtils.generateToken(jwtInfo);
    }
}
