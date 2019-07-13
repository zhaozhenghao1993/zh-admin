package com.zhenghao.admin.common.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 🙃
 * 🙃 树节点实体类父类，树节点都继承此类
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/05/28 21:04
 * TreeNode.java
 */

public class TreeNode {

    private Long id;

    private Long parentId;

    private List<TreeNode> children = new ArrayList<>();

    public TreeNode() {
    }

    public TreeNode(Long id, Long parentId) {
        this.id = id;
        this.parentId = parentId;
    }

    public void addChild(TreeNode node) {
        children.add(node);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", children=" + children +
                '}';
    }
}
