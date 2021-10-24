package com.zhenghao.admin.server.controller;

import com.zhenghao.admin.server.handler.context.RequestContextHandler;

/**
 * Controller公共组件
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2017年11月22日 下午3:03:45
 * AbstractController.java
 */
public abstract class AbstractController {

    /**
     * 获取当前请求的用户id
     *
     * @return
     */
    protected Long getUserId() {
        return RequestContextHandler.getUserId();
    }

    /**
     * 获取当前请求的用户username
     *
     * @return
     */
    protected String getUserName() {
        return RequestContextHandler.getUsername();
    }
}
