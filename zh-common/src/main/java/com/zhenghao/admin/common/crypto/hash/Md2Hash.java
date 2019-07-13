package com.zhenghao.admin.common.crypto.hash;

import com.zhenghao.admin.common.crypto.codec.Base64;
import com.zhenghao.admin.common.crypto.codec.Hex;
import com.zhenghao.admin.common.crypto.codec.Base64;
import com.zhenghao.admin.common.crypto.codec.Hex;

/**
 * 🙃
 * 🙃
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/05/11 15:58
 * Md2Hash.java
 */

public class Md2Hash extends SimpleHash {

    public static final String ALGORITHM_NAME = "MD2";

    public Md2Hash() {
        super("MD2");
    }

    public Md2Hash(Object source) {
        super("MD2", source);
    }

    public Md2Hash(Object source, Object salt) {
        super("MD2", source, salt);
    }

    public Md2Hash(Object source, Object salt, int hashIterations) {
        super("MD2", source, salt, hashIterations);
    }

    public static Md2Hash fromHexString(String hex) {
        Md2Hash hash = new Md2Hash();
        hash.setBytes(Hex.decode(hex));
        return hash;
    }

    public static Md2Hash fromBase64String(String base64) {
        Md2Hash hash = new Md2Hash();
        hash.setBytes(Base64.decode(base64));
        return hash;
    }
}
