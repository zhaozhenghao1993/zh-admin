package net.zhenghao.zh.common.handler;

import net.zhenghao.zh.common.constant.HttpStatusConstant;
import net.zhenghao.zh.common.entity.R;
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

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public R otherExceptionHandler(HttpServletResponse response, Exception ex) {
        response.setStatus(500);
        logger.error(ex.getMessage(), ex);
        return R.error(HttpStatusConstant.EXCEPTION_OTHER_CODE, ex.toString());
    }
}
