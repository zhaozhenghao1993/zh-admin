package net.zhenghao.zh.common.util;

import net.zhenghao.zh.common.entity.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 🙃
 * 🙃 响应工具类
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/07/06 21:29
 * ResponseUtils.java
 */

public class ResponseUtils {

    private ResponseUtils() {
    }

    /**
     * 设置响应封装
     *
     * @param response HttpServletResponse
     * @param result result 公共响应类
     * @throws IOException
     */
    public static void setResultResponse(HttpServletResponse response, Result result) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter()
                .write(JSONUtils.objToString(result));
    }
}
