package net.zhenghao.zh.common.utils;

import net.zhenghao.zh.common.jwt.JWTHelper;
import net.zhenghao.zh.common.jwt.JWTInfo;
import net.zhenghao.zh.common.jwt.RsaKeyManager;
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

    @Autowired
    private RsaKeyManager rsaKeyManager;

    /**
     * 私钥生成jwt token
     * @param jwtInfo
     * @return
     * @throws Exception
     */
    public String generateToken(JWTInfo jwtInfo) throws Exception {
        return JWTHelper.generateToken(jwtInfo, rsaKeyManager.getPrivateKey(), expire);
    }

    /**
     * 公钥解析jwt token
     * @param token
     * @return
     * @throws Exception
     */
    public JWTInfo getInfoFromToken(String token) throws Exception {
        return JWTHelper.getInfoFromToken(token, rsaKeyManager.getPublicKey());
    }
}
