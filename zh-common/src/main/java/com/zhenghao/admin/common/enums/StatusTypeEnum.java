package com.zhenghao.admin.common.enums;

/**
 * 通用变量,表示 可用、禁用、显示、隐藏
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2017年12月6日 下午2:17:36
 * SystemConstants.java
 */
public enum StatusTypeEnum {

    /**
     * 可用,显示,成功
     */
    ENABLE(0),

    /**
     * 禁用,隐藏,失败,锁定
     */
    DISABLE(1),

    /**
     * 显示
     */
    SHOW(0),

    /**
     * 隐藏
     */
    HIDDEN(1);

    private int value;

    private StatusTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
