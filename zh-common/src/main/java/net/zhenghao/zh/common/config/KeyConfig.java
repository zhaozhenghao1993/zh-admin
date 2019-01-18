package net.zhenghao.zh.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 🙃
 * 🙃 RSA 公钥密钥配置
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/01/18 23:51
 * KeyConfig.java
 */

@Configuration
public class KeyConfig {

    @Value("${zh-admin.jwt.rsa-secret}")
    private String rsaSecret;

    private byte[] pubKey;

    private byte[] priKey;

    public String getRsaSecret() {
        return rsaSecret;
    }

    public void setRsaSecret(String rsaSecret) {
        this.rsaSecret = rsaSecret;
    }

    public byte[] getPubKey() {
        return pubKey;
    }

    public void setPubKey(byte[] pubKey) {
        this.pubKey = pubKey;
    }

    public byte[] getPriKey() {
        return priKey;
    }

    public void setPriKey(byte[] priKey) {
        this.priKey = priKey;
    }
}
