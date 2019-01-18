package net.zhenghao.zh.common.jwt;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

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
     * 生成公钥和密钥
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static Map<String, byte[] > generateKey(String password) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = new SecureRandom(password.getBytes());
        keyPairGenerator.initialize(1024, secureRandom);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
        Map<String, byte[]> map = new HashMap<String, byte[]>();
        map.put("pub", publicKeyBytes);
        map.put("pri", privateKeyBytes);
        return map;
    }

    /**
     * 生成rsa公钥
     *
     * @param password
     * @throws NoSuchAlgorithmException
     */
    public static byte[] generatePublicKey(String password) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = new SecureRandom(password.getBytes());
        keyPairGenerator.initialize(1024, secureRandom);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        return keyPair.getPublic().getEncoded();
    }

    /**
     * 生成rsa公钥
     *
     * @param password
     * @throws NoSuchAlgorithmException
     */
    public static byte[] generatePrivateKey(String password) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = new SecureRandom(password.getBytes());
        keyPairGenerator.initialize(1024, secureRandom);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        return keyPair.getPrivate().getEncoded();
    }

    /**
     * 获取公钥
     *
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(byte[] publicKey) throws Exception {
        X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    /**
     * 获取密钥
     *
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(byte[] privateKey) throws Exception {
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    public static void main(String[] args) throws Exception {
        Map<String, byte[]> map = generateKey("111");
        PublicKey publicKey = getPublicKey(map.get("pub"));
        PrivateKey privateKey = getPrivateKey(map.get("pri"));
    }
}
