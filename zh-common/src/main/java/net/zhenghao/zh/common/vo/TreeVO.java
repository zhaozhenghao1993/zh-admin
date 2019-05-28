package net.zhenghao.zh.common.vo;

import net.zhenghao.zh.common.entity.TreeNode;

import java.io.Serializable;
import java.util.List;

/**
 * 🙃
 * 🙃
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/03/23 14:50
 * TreeVO.java
 */

public class TreeVO extends TreeNode implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long key;

    private String value;

    private String title;

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
