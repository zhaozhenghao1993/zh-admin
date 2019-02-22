package net.zhenghao.zh.common.jwt;

import java.io.InputStream;
import java.security.*;
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

    /**
     * 加载公钥
     *
     * @param filename
     * @return
     * @throws Exception
     */
    public PublicKey loadPublicKey(String filename) throws Exception {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(filename);
        byte[] bytes = new byte[resourceAsStream.available()];
        resourceAsStream.read(bytes);
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
    public PrivateKey loadPrivateKey(String filename) throws Exception {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(filename);
        byte[] bytes = new byte[resourceAsStream.available()];
        resourceAsStream.read(bytes);
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] buffer= decoder.decode(new String(bytes));
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(buffer);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }
}
