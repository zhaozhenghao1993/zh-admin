package com.zhenghao.admin.common.exception.upload;

import com.zhenghao.admin.common.constant.HttpStatusConstants;
import com.zhenghao.admin.common.exception.BaseException;

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
        super(HttpStatusConstants.EXCEPTION_OTHER_CODE, message);
    }

    public UploadException(int code, String message) {
        super(code, message);
    }
}
