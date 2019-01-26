package net.zhenghao.zh.common.handler;

import net.zhenghao.zh.common.entity.R;
import net.zhenghao.zh.common.exception.auth.ApiInvalidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ApiInvalidException.class)
    public R apiInvalidExceptionHandler(HttpServletResponse response, ApiInvalidException ex) {
        response.setStatus(404);
        LOGGER.error(ex.getMessage(), ex);
        return R.error(ex.getCode(), ex.getMessage());
    }
}
