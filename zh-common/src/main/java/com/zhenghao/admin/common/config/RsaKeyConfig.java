package com.zhenghao.admin.common.config;

import com.zhenghao.admin.common.jwt.RsaKeyHelper;
import com.zhenghao.admin.common.jwt.RsaKeyHelper;
import com.zhenghao.admin.common.jwt.RsaKeyManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

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
public class RsaKeyConfig {

    @Value("${zh-admin.jwt.private-key-path}")
    private String privateKeyPath;

    @Value("${zh-admin.jwt.public-key-path}")
    private String publicKeyPath;

    @Bean
    public RsaKeyManager getRsaKeyManager() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        RsaKeyHelper rsaKeyHelper = new RsaKeyHelper();
        RsaKeyManager rsaKeyManager = new RsaKeyManager();
        rsaKeyManager.setPublicKey(rsaKeyHelper.loadPublicKey(publicKeyPath));
        rsaKeyManager.setPrivateKey(rsaKeyHelper.loadPrivateKey(privateKeyPath));
        return rsaKeyManager;
    }
}
