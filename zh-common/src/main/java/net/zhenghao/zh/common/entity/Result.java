package net.zhenghao.zh.common.entity;

/**
 * 🙃
 * 🙃 公共响应类
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/05/18 21:47
 * Result.java
 */

public class Result<R> {

    private boolean success;

    private int code;

    private String msg;

    private R data;

    public boolean isSuccess() {
        return success;
    }

    public Result<R> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public int getCode() {
        return code;
    }

    public Result<R> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result<R> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public R getData() {
        return data;
    }

    public Result<R> setData(R data) {
        this.data = data;
        return this;
    }

    public static <R> Result<R> ofSuccess(R data) {
        return new Result<R>()
                .setSuccess(true)
                .setMsg("success")
                .setData(data);
    }

    public static <R> Result<R> ofSuccessMsg(String msg) {
        return new Result<R>()
                .setSuccess(true)
                .setMsg(msg);
    }

    public static <R> Result<R> ofFail(String msg) {
        return new Result<R>()
                .setSuccess(false)
                .setCode(500)
                .setMsg(msg);
    }

    public static <R> Result<R> ofFail(int code, String msg) {
        return new Result<R>()
                .setSuccess(false)
                .setCode(code)
                .setMsg(msg);
    }

    public static <R> Result<R> ofThrowable(int code, Throwable throwable) {
        return new Result<R>()
                .setSuccess(false)
                .setCode(code)
                .setMsg(throwable.getClass().getName() + ", " + throwable.getMessage());
    }

    /**
     * 只获取异常message
     *
     * @param code
     * @param throwable
     * @param <R>
     * @return
     */
    public static <R> Result<R> ofThrowableMsg(int code, Throwable throwable) {
        return new Result<R>()
                .setSuccess(false)
                .setCode(code)
                .setMsg(throwable.getMessage());
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
