package com.zhenghao.admin.common.jwt;

/**
 * 🙃
 * 🙃 JWT TOKEN 处理器
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/01/18 23:23
 * JWTTokenUtils.java
 */
public class JWTTokenProcessor {

    private final int expire;

    private final RsaKeyManager rsaKeyManager;

    public JWTTokenProcessor(int expire, RsaKeyManager rsaKeyManager) {
        this.expire = expire;
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
