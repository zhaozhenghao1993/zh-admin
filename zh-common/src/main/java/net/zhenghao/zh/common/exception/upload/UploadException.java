package net.zhenghao.zh.common.exception.upload;

import net.zhenghao.zh.common.constant.HttpStatusConstant;
import net.zhenghao.zh.common.exception.BaseException;

/**
 * 🙃
 * 🙃 上传异常
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/01/24 23:16
 * UserTokenException.java
 */

public class UploadException extends BaseException {

    public UploadException(String message) {
        super(HttpStatusConstant.EXCEPTION_OTHER_CODE, message);
    }

    public UploadException(int code, String message) {
        super(code, message);
    }
}
