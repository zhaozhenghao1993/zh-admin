package com.zhenghao.admin.api.entity;

import java.io.Serializable;

/**
 * 🙃
 * 🙃
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2021/10/23 20:56
 * SysDictItem.java
 */
public class SysDictItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字典元素名称
     */
    private String name;

    /**
     * 字典元素值
     */
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
