package com.zhenghao.admin.common.handler;

import com.zhenghao.admin.common.exception.upload.UploadException;
import com.zhenghao.admin.common.constant.HttpStatusConstant;
import com.zhenghao.admin.common.entity.Result;
import com.zhenghao.admin.common.exception.upload.UploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * 🙃
 * 🙃 全局异常处理类
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/01/26 22:48
 * GlobalExceptionHandler.java
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UploadException.class)
    public Result uploadExceptionHandler(HttpServletResponse response, UploadException ex) {
        response.setStatus(HttpServletResponse.SC_OK);
        logger.error(ex.getMessage(), ex);
        return Result.ofThrowableMsg(HttpStatusConstant.EXCEPTION_OTHER_CODE, ex);
    }

    /**
     * 当请求被 xss 拦截后 json 串可能被破坏，将其异常捕获
     * @param response
     * @param ex
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result httpMessageNotReadableExceptionHandler(HttpServletResponse response, HttpMessageNotReadableException ex) {
        response.setStatus(HttpServletResponse.SC_OK);
        logger.error(ex.getMessage(), ex);
        return Result.ofFail("json格式转换异常,请检查当前表单输入是否正确!");
    }

    @ExceptionHandler(Exception.class)
    public Result otherExceptionHandler(HttpServletResponse response, Exception ex) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        logger.error(ex.getMessage(), ex);
        return Result.ofThrowable(HttpStatusConstant.EXCEPTION_OTHER_CODE, ex);
    }
}
