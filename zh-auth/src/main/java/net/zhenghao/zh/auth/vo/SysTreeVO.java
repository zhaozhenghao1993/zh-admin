package net.zhenghao.zh.auth.vo;

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
 * SysTreeVO.java
 */

public class SysTreeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long key;

    private String parentId;

    private String value;

    private String title;

    private List<SysTreeVO> children;

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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

    public List<SysTreeVO> getChildren() {
        return children;
    }

    public void setChildren(List<SysTreeVO> children) {
        this.children = children;
    }
}
