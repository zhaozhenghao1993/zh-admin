package net.zhenghao.zh.common.crypto.hash;

import net.zhenghao.zh.common.crypto.codec.Base64;
import net.zhenghao.zh.common.crypto.codec.CodecException;
import net.zhenghao.zh.common.crypto.codec.Hex;
import net.zhenghao.zh.common.crypto.UnknownAlgorithmException;
import net.zhenghao.zh.common.crypto.util.ByteSource;
import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 🙃
 * 🙃
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/05/11 15:16
 * SimpleHash.java
 */

public class SimpleHash extends AbstractHash {

    private static final int DEFAULT_ITERATIONS = 1;
    private final String algorithmName;
    private byte[] bytes;
    private ByteSource salt;
    private int iterations;
    private transient String hexEncoded;
    private transient String base64Encoded;

    public SimpleHash(String algorithmName) {
        this.hexEncoded = null;
        this.base64Encoded = null;
        this.algorithmName = algorithmName;
        this.iterations = 1;
    }

    public SimpleHash(String algorithmName, Object source) throws CodecException, UnknownAlgorithmException {
        this(algorithmName, source, (Object)null, 1);
    }

    public SimpleHash(String algorithmName, Object source, Object salt) throws CodecException, UnknownAlgorithmException {
        this(algorithmName, source, salt, 1);
    }

    public SimpleHash(String algorithmName, Object source, Object salt, int hashIterations) throws CodecException, UnknownAlgorithmException {
        this.hexEncoded = null;
        this.base64Encoded = null;
        if (!StringUtils.isNotBlank(algorithmName)) {
            throw new NullPointerException("algorithmName argument cannot be null or empty.");
        } else {
            this.algorithmName = algorithmName;
            this.iterations = Math.max(1, hashIterations);
            ByteSource saltBytes = null;
            if (salt != null) {
                saltBytes = this.convertSaltToBytes(salt);
                this.salt = saltBytes;
            }

            ByteSource sourceBytes = this.convertSourceToBytes(source);
            this.hash(sourceBytes, saltBytes, hashIterations);
        }
    }

    protected ByteSource convertSourceToBytes(Object source) {
        return this.toByteSource(source);
    }

    protected ByteSource convertSaltToBytes(Object salt) {
        return this.toByteSource(salt);
    }

    protected ByteSource toByteSource(Object o) {
        if (o == null) {
            return null;
        } else if (o instanceof ByteSource) {
            return (ByteSource)o;
        } else {
            byte[] bytes = this.toBytes(o);
            return ByteSource.Util.bytes(bytes);
        }
    }

    private void hash(ByteSource source, ByteSource salt, int hashIterations) throws CodecException, UnknownAlgorithmException {
        byte[] saltBytes = salt != null ? salt.getBytes() : null;
        byte[] hashedBytes = this.hash(source.getBytes(), saltBytes, hashIterations);
        this.setBytes(hashedBytes);
    }

    public String getAlgorithmName() {
        return this.algorithmName;
    }

    public ByteSource getSalt() {
        return this.salt;
    }

    public int getIterations() {
        return this.iterations;
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    public void setBytes(byte[] alreadyHashedBytes) {
        this.bytes = alreadyHashedBytes;
        this.hexEncoded = null;
        this.base64Encoded = null;
    }

    public void setIterations(int iterations) {
        this.iterations = Math.max(1, iterations);
    }

    public void setSalt(ByteSource salt) {
        this.salt = salt;
    }

    protected MessageDigest getDigest(String algorithmName) throws UnknownAlgorithmException {
        try {
            return MessageDigest.getInstance(algorithmName);
        } catch (NoSuchAlgorithmException var4) {
            String msg = "No native '" + algorithmName + "' MessageDigest instance available on the current JVM.";
            throw new UnknownAlgorithmException(msg, var4);
        }
    }

    protected byte[] hash(byte[] bytes) throws UnknownAlgorithmException {
        return this.hash((byte[])bytes, (byte[])null, 1);
    }

    protected byte[] hash(byte[] bytes, byte[] salt) throws UnknownAlgorithmException {
        return this.hash((byte[])bytes, (byte[])salt, 1);
    }

    protected byte[] hash(byte[] bytes, byte[] salt, int hashIterations) throws UnknownAlgorithmException {
        MessageDigest digest = this.getDigest(this.getAlgorithmName());
        if (salt != null) {
            digest.reset();
            digest.update(salt);
        }

        byte[] hashed = digest.digest(bytes);
        int iterations = hashIterations - 1;

        for(int i = 0; i < iterations; ++i) {
            digest.reset();
            hashed = digest.digest(hashed);
        }

        return hashed;
    }

    public boolean isEmpty() {
        return this.bytes == null || this.bytes.length == 0;
    }

    public String toHex() {
        if (this.hexEncoded == null) {
            this.hexEncoded = Hex.encodeToString(this.getBytes());
        }

        return this.hexEncoded;
    }

    public String toBase64() {
        if (this.base64Encoded == null) {
            this.base64Encoded = Base64.encodeToString(this.getBytes());
        }

        return this.base64Encoded;
    }

    public String toString() {
        return this.toHex();
    }

    public boolean equals(Object o) {
        if (o instanceof Hash) {
            Hash other = (Hash)o;
            return MessageDigest.isEqual(this.getBytes(), other.getBytes());
        } else {
            return false;
        }
    }

    public int hashCode() {
        return this.bytes != null && this.bytes.length != 0 ? Arrays.hashCode(this.bytes) : 0;
    }
}
