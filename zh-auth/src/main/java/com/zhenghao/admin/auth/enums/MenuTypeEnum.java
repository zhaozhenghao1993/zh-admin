package com.zhenghao.admin.auth.enums;

/**
 * 菜单类型枚举类
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2017年12月6日 下午2:17:36
 * MenuTypeEnum.java
 */
public enum MenuTypeEnum {

    /**
     * 目录
     */
    CATALOG(0),

    /**
     * 菜单
     */
    MENU(1),

    /**
     * 按钮
     */
    BUTTON(2),

    /**
     * 链接
     */
    LINK(3);

    private int value;

    private MenuTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
