package com.zhenghao.admin.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 🙃
 * 🙃 权限JWT配置类
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2020/03/22 16:15
 * JWTProperties.java
 */
@ConfigurationProperties(prefix = "zh-admin.jwt")
public class JWTProperties {

    /**
     * JWT expire time, Units are seconds
     * default value: 14400s
     */
    private int expire = 14400;

    /**
     * public key path
     * default value: jwt/rsa_public_key.pem
     */
    private String publicKeyPath = "jwt/rsa_public_key.pem";

    /**
     * private key path
     * default value: jwt/pkcs8_rsa_private_key.pem
     */
    private String privateKeyPath = "jwt/pkcs8_rsa_private_key.pem";

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public String getPublicKeyPath() {
        return publicKeyPath;
    }

    public void setPublicKeyPath(String publicKeyPath) {
        this.publicKeyPath = publicKeyPath;
    }

    public String getPrivateKeyPath() {
        return privateKeyPath;
    }

    public void setPrivateKeyPath(String privateKeyPath) {
        this.privateKeyPath = privateKeyPath;
    }

}
