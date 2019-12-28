package com.zhenghao.admin.common.util;

import com.zhenghao.admin.common.jwt.JWTHelper;
import com.zhenghao.admin.common.jwt.JWTInfo;
import com.zhenghao.admin.common.jwt.RsaKeyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 🙃
 * 🙃 JWT TOKEN 工具类
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/01/18 23:23
 * JWTTokenUtils.java
 */
@Component
public class JWTTokenUtils {

    @Value("${zh-admin.jwt.expire}")
    private int expire;

    private final RsaKeyManager rsaKeyManager;

    @Autowired
    public JWTTokenUtils(RsaKeyManager rsaKeyManager) {
        this.rsaKeyManager = rsaKeyManager;
    }

    /**
     * 私钥生成jwt token
     *
     * @param jwtInfo
     * @return
     * @throws Exception
     */
    public String generateToken(JWTInfo jwtInfo) {
        return JWTHelper.generateToken(jwtInfo, rsaKeyManager.getPrivateKey(), expire);
    }

    /**
     * 公钥解析jwt token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public JWTInfo getInfoFromToken(String token) {
        return JWTHelper.getInfoFromToken(token, rsaKeyManager.getPublicKey());
    }
}
