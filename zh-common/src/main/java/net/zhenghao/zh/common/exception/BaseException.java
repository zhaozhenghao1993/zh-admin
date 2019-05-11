package net.zhenghao.zh.common.exception;

/**
 * 🙃
 * 🙃 基础异常类
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/01/23 23:15
 * BaseException.java
 */

public class BaseException extends RuntimeException {

    private int code = 500;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public BaseException() {
    }

    public BaseException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }
}
