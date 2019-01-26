package net.zhenghao.zh.common.exception.auth;

import net.zhenghao.zh.common.constant.HttpStatusConstant;
import net.zhenghao.zh.common.exception.BaseException;

/**
 * 🙃
 * 🙃 API 异常
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/01/26 22:58
 * ApiInvalidException.java
 */

public class ApiInvalidException extends BaseException {

    public ApiInvalidException(int code, String message) {
        super(code, message);
    }

    public ApiInvalidException(String message) {
        super(HttpStatusConstant.REQUEST_API_INVALID, message);
    }
}
