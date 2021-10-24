package com.zhenghao.admin.common.exception.upload;

import com.zhenghao.admin.common.constant.HttpStatusConstants;

/**
 * 🙃
 * 🙃 上传文件大小异常
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/01/24 23:16
 * UserTokenException.java
 */

public class UploadSizeException extends UploadException {

    public UploadSizeException(String message) {
        super(HttpStatusConstants.EXCEPTION_OTHER_CODE, message);
    }

    public UploadSizeException(int code, String message) {
        super(code, message);
    }
}
