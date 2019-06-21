package net.zhenghao.zh.common.controller;

import net.zhenghao.zh.common.context.BaseContextHandler;

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
     * 获取当前请求token的用户id
     *
     * @return
     */
    protected Long getUserId() {
        return BaseContextHandler.getUserId();
    }

    /**
     * 获取当前请求token的用户username
     *
     * @return
     */
    protected String getUserName() {
        return BaseContextHandler.getUsername();
    }

    /**
     * 获取当前请求token的用户name
     *
     * @return
     */
    protected String getName() {
        return BaseContextHandler.getName();
    }
}
