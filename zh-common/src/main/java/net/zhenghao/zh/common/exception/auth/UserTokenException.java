package net.zhenghao.zh.common.exception.auth;

import net.zhenghao.zh.common.constant.HttpStatusConstant;
import net.zhenghao.zh.common.exception.BaseException;

/**
 * 🙃
 * 🙃 token 异常
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/01/24 23:16
 * UserTokenException.java
 */

public class UserTokenException extends BaseException {

    public UserTokenException(String message) {
        super(HttpStatusConstant.TOKEN_SIGNATURE_ERROR, message);
    }

    public UserTokenException(String message, int code) {
        super(code, message);
    }
}
