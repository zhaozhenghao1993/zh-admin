package net.zhenghao.zh.common.jwt;

import sun.misc.BASE64Decoder;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
    public static Map<String, byte[]> generateKey(String password) throws NoSuchAlgorithmException {
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
     * @param filename
     * @return
     * @throws Exception
     */
    public PublicKey getPublicKey(String filename) throws Exception {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(filename);
        DataInputStream dis = new DataInputStream(resourceAsStream);
        byte[] keyBytes = new byte[resourceAsStream.available()];
        dis.readFully(keyBytes);
        dis.close();
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }


    private final static String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALnXBChpTNJWP7+N\n" +
            "bolwNKc3eLDVdh2I2j8pVI/zZCTQXr5X7Yw255Kns+YhntlZXAKG8c0A2KE7TRuf\n" +
            "mziaD2pPwr7K8AuaAo67jXmV8498TperBmkbcDyyUSmb4wPLC32G18NZXD4+hwaC\n" +
            "eio99DMtUuG2gK0dKiUxDi+eeag/AgMBAAECgYBDcWzo3Y4rp3FrKwC6kCYLqW37\n" +
            "fvxmq4vI8E7oDwSA973qhi6HfEV+S65WDk1/w5ZItIB8TZpQqRzKsZm7aO2+oOhY\n" +
            "n9lKp7Na7LxPsww3JSiLXMticKq/1EjZJo1g91S6k/cQI93+ig1c0jAJUahrnNBk\n" +
            "B9n8r/QsnKhKVqRjgQJBAN8fbv7TrT26wWFBzJj89OjGadKjA+ZOlQqspfk0PFbn\n" +
            "SRhFSj0duoi7wE1ez/c85p8FmVk4Vq8cCvjtHJooLocCQQDVOTW0UwU6SgNBayTF\n" +
            "jbNGS8/B7W9pK89ygar+UVtWSWIP1doJaBQzCiU7mUqWFtKR9iHJBoCEFuwkcZir\n" +
            "H66JAkAQz8ODO9vPzOSJOXX7CQRDDcAneNTwY3djaILQ9hidy6t8QqxHUpgdeVgR\n" +
            "/FTbIiGWVcuqJ06QvyIsg2EmVGS9AkEAu0y6+UEoSXC0dWXJ8mOsBGcaBC8u+8LJ\n" +
            "QrvW2fnbcuuZYXGoSzUK8V7LIBRi6X74z9DXEsBMaju8ibLjugZliQJAVtQbF02N\n" +
            "cRwSbumKV3uPt4k95ach7VTjEOEGEwJg2kx+79M6sHnghWq5q8p0B/knrWGWSM0W\n" +
            "On/1Fwp4sWJptw==";
    /**
     * 获取密钥
     *
     * @param filename
     * @return
     * @throws Exception
     */
    public PrivateKey getPrivateKey(String filename) throws Exception {
        /*InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(filename);
        DataInputStream dis = new DataInputStream(resourceAsStream);
        byte[] keyBytes = new byte[resourceAsStream.available()];
        dis.readFully(keyBytes);
        dis.close();
        */

        BASE64Decoder base64Decoder= new BASE64Decoder();
        byte[] buffer= base64Decoder.decodeBuffer(PRIVATE_KEY);



        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(buffer);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
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
        /*Map<String, byte[]> map = generateKey("111");
        PublicKey publicKey = getPublicKey(map.get("pub"));
        PrivateKey privateKey = getPrivateKey(map.get("pri"));*/
        RsaKeyHelper rsaKeyHelper = new RsaKeyHelper();
        rsaKeyHelper.getPrivateKey("classpath:jwt/rsa_private_key.pem");
        rsaKeyHelper.getPublicKey("classpath:jwt/rsa_public_key.pem");
    }
}
