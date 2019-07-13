package com.zhenghao.admin.common.jwt;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 🙃
 * 🙃 RSA 公钥密钥管理类
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/02/17 10:13
 * RsaKeyManager.java
 */

public class RsaKeyManager {

    private PrivateKey privateKey;

    private PublicKey publicKey;

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }
}
