package com.zhenghao.admin.common.enums;

/**
 * 日志类型
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2018年2月9日 上午11:17:36
 * LogType.java
 */
public enum LogTypeEnum {

    /**
     * 登录登出日志
     */
    LOGIN(1),

    /**
     * 访问日志
     */
    ACCESS(2),

    /**
     * 操作日志
     */
    OPERATION(3),

    /**
     * 异常日志
     */
    ERROR(4),

    /**
     * 授权日志
     */
    AUTHORIZATION(5);

    private int value;

    private LogTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
