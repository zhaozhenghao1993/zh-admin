package com.zhenghao.admin.common.constant;


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

public class HttpStatusConstants {

    private HttpStatusConstants() {
    }

    /**
     * 用户没登陆
     */
    public static final int USER_API_UNAUTHORIZED = 401;

    /**
     * 该用户对此api无权限
     */
    public static final int USER_API_FORBIDDEN = 403;

    /**
     * 其他异常code
     */
    public static final int EXCEPTION_OTHER_CODE = 500;
}
