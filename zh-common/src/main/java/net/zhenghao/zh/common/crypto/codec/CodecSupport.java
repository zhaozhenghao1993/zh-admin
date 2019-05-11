package net.zhenghao.zh.common.crypto.codec;

import net.zhenghao.zh.common.crypto.util.ByteSource;

import java.io.*;

/**
 * 🙃
 * 🙃
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/05/11 15:23
 * CodecSupport.java
 */

public abstract class CodecSupport {

    public static final String PREFERRED_ENCODING = "UTF-8";

    public CodecSupport() {
    }

    public static byte[] toBytes(char[] chars) {
        return toBytes(new String(chars), "UTF-8");
    }

    public static byte[] toBytes(char[] chars, String encoding) throws CodecException {
        return toBytes(new String(chars), encoding);
    }

    public static byte[] toBytes(String source) {
        return toBytes(source, "UTF-8");
    }

    public static byte[] toBytes(String source, String encoding) throws CodecException {
        try {
            return source.getBytes(encoding);
        } catch (UnsupportedEncodingException var4) {
            String msg = "Unable to convert source [" + source + "] to byte array using " + "encoding '" + encoding + "'";
            throw new CodecException(msg, var4);
        }
    }

    public static String toString(byte[] bytes) {
        return toString(bytes, "UTF-8");
    }

    public static String toString(byte[] bytes, String encoding) throws CodecException {
        try {
            return new String(bytes, encoding);
        } catch (UnsupportedEncodingException var4) {
            String msg = "Unable to convert byte array to String with encoding '" + encoding + "'.";
            throw new CodecException(msg, var4);
        }
    }

    public static char[] toChars(byte[] bytes) {
        return toChars(bytes, "UTF-8");
    }

    public static char[] toChars(byte[] bytes, String encoding) throws CodecException {
        return toString(bytes, encoding).toCharArray();
    }

    protected boolean isByteSource(Object o) {
        return o instanceof byte[] || o instanceof char[] || o instanceof String || o instanceof ByteSource || o instanceof File || o instanceof InputStream;
    }

    protected byte[] toBytes(Object o) {
        if (o == null) {
            String msg = "Argument for byte conversion cannot be null.";
            throw new IllegalArgumentException(msg);
        } else if (o instanceof byte[]) {
            return (byte[])((byte[])o);
        } else if (o instanceof ByteSource) {
            return ((ByteSource)o).getBytes();
        } else if (o instanceof char[]) {
            return toBytes((char[])((char[])o));
        } else if (o instanceof String) {
            return toBytes((String)o);
        } else if (o instanceof File) {
            return this.toBytes((File)o);
        } else {
            return o instanceof InputStream ? this.toBytes((InputStream)o) : this.objectToBytes(o);
        }
    }

    protected String toString(Object o) {
        if (o == null) {
            String msg = "Argument for String conversion cannot be null.";
            throw new IllegalArgumentException(msg);
        } else if (o instanceof byte[]) {
            return toString((byte[])((byte[])o));
        } else if (o instanceof char[]) {
            return new String((char[])((char[])o));
        } else {
            return o instanceof String ? (String)o : this.objectToString(o);
        }
    }

    protected byte[] toBytes(File file) {
        if (file == null) {
            throw new IllegalArgumentException("File argument cannot be null.");
        } else {
            try {
                return this.toBytes((InputStream)(new FileInputStream(file)));
            } catch (FileNotFoundException var4) {
                String msg = "Unable to acquire InputStream for file [" + file + "]";
                throw new CodecException(msg, var4);
            }
        }
    }

    protected byte[] toBytes(InputStream in) {
        if (in == null) {
            throw new IllegalArgumentException("InputStream argument cannot be null.");
        } else {
            ByteArrayOutputStream out = new ByteArrayOutputStream(512);
            byte[] buffer = new byte[512];

            byte[] var6;
            try {
                int bytesRead;
                while((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }

                var6 = out.toByteArray();
            } catch (IOException var18) {
                throw new CodecException(var18);
            } finally {
                try {
                    in.close();
                } catch (IOException var17) {
                    ;
                }

                try {
                    out.close();
                } catch (IOException var16) {
                    ;
                }

            }

            return var6;
        }
    }

    protected byte[] objectToBytes(Object o) {
        String msg = "The " + this.getClass().getName() + " implementation only supports conversion to " + "byte[] if the source is of type byte[], char[], String, " + ByteSource.class.getName() + " File or InputStream.  The instance provided as a method " + "argument is of type [" + o.getClass().getName() + "].  If you would like to convert " + "this argument type to a byte[], you can 1) convert the argument to one of the supported types " + "yourself and then use that as the method argument or 2) subclass " + this.getClass().getName() + "and override the objectToBytes(Object o) method.";
        throw new CodecException(msg);
    }

    protected String objectToString(Object o) {
        return o.toString();
    }
}
