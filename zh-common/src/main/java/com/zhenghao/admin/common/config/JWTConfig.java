package com.zhenghao.admin.common.config;

import com.zhenghao.admin.common.jwt.JWTTokenProcessor;
import com.zhenghao.admin.common.jwt.RsaKeyHelper;
import com.zhenghao.admin.common.jwt.RsaKeyManager;
import com.zhenghao.admin.common.properties.JWTProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * 🙃
 * 🙃 JWT 配置类
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/01/18 23:51
 * KeyConfig.java
 */

/*@Configuration
@EnableConfigurationProperties(JWTProperties.class)*/
public class JWTConfig {

    private final JWTProperties jwtProperties;

    public JWTConfig(JWTProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    /**
     * RSA 公钥密钥配置
     *
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws InvalidKeySpecException
     */
    @Bean
    public RsaKeyManager rsaKeyManager() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        RsaKeyHelper rsaKeyHelper = new RsaKeyHelper();
        RsaKeyManager rsaKeyManager = new RsaKeyManager();
        rsaKeyManager.setPublicKey(rsaKeyHelper.loadPublicKey(jwtProperties.getPublicKeyPath()));
        rsaKeyManager.setPrivateKey(rsaKeyHelper.loadPrivateKey(jwtProperties.getPrivateKeyPath()));
        return rsaKeyManager;
    }

    /**
     * 注入 JWT TOKEN 处理器
     * @param rsaKeyManager
     * @return
     */
    @Bean
    public JWTTokenProcessor jwtTokenProcessor(RsaKeyManager rsaKeyManager) {
        return new JWTTokenProcessor(jwtProperties.getExpire(), rsaKeyManager);
    }
}
