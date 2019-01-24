package net.zhenghao.zh.common.constant;


/**
 * 🙃
 * 🙃 请求响应状态标识
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/01/22 21:28
 * HttpStatusConstant.java
 */

public class HttpStatusConstant {

    /**
     * 该用户对此api无权限
     */
    public static final int USER_API_UNAUTHORIZED = 40101;

    /**
     * token 过期
     */
    public static final int TOKEN_EXPIRED_FORBIDDEN = 40301;

    /**
     * token 签名异常
     */
    public static final int TOKEN_SIGNATURE_ERROR = 40302;

    /**
     * token 为空
     */
    public static final int TOKEN_NULL_FORBIDDEN = 40303;

    /**
     * 请求 api 格式错误,请求无效
     */
    public static final int REQUEST_API_INVALID = 40401;

    /**
     * 请求 api 不存在
     */
    public static final int REQUEST_API_NOT_FOUND = 40402;
}
