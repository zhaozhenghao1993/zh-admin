package net.zhenghao.zh.common.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * 🙃
 * 🙃 RsaKey密钥辅助类
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/01/14 22:58
 * RsaKeyHelper.java
 */

public class RsaKeyHelper {

    private static final Logger logger = LoggerFactory.getLogger(RsaKeyHelper.class);

    /**
     * 加载公钥
     *
     * @param filename
     * @return
     * @throws Exception
     */
    public PublicKey loadPublicKey(String filename) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(filename);
        byte[] bytes = new byte[resourceAsStream.available()];
        int total = resourceAsStream.read(bytes);
        logger.info("PublicKey File read successfully, the total number of bytes read for {}", total);
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] buffer= decoder.decode(new String(bytes));
        X509EncodedKeySpec spec = new X509EncodedKeySpec(buffer);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    /**
     * 加载密钥
     *
     * @param filename
     * @return
     * @throws Exception
     */
    public PrivateKey loadPrivateKey(String filename) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(filename);
        byte[] bytes = new byte[resourceAsStream.available()];
        int total = resourceAsStream.read(bytes);
        logger.info("PrivateKey File read successfully, the total number of bytes read for {}", total);
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] buffer= decoder.decode(new String(bytes));
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(buffer);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }
}
