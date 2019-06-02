package net.zhenghao.zh.common.utils;

import net.zhenghao.zh.common.entity.TreeNode;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 🙃
 * 🙃
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/05/28 21:03
 * TreeUtils.java
 */

public class TreeUtils {

    private TreeUtils() {
    }

    /**
     * 递归循环实现建树
     * @param trees 传入的树节点列表
     * @param parentId  根节点的parentId
     * @return
     */
    public static <T extends TreeNode> List<T> build(List<T> trees, Long parentId) {
        List<T> treeRoot = trees.stream().filter(tree -> parentId.equals(tree.getParentId())).collect(Collectors.toList());
        treeRoot.forEach(root -> getTree(trees, root));
        return treeRoot;
    }

    /**
     * 递归获取无限级子节点
     *
     * @param trees         传入的树节点列表
     * @param parentTree    当前父节点
     * @return
     */
    private static <T extends TreeNode> T getTree(List<T> trees, T parentTree) {
        List<T> childList = trees.stream().filter(tree -> parentTree.getId().equals(tree.getParentId())).collect(Collectors.toList());
        if (!childList.isEmpty()) {
            childList.forEach(child -> parentTree.addChild(getTree(trees, child)));
        }
        return parentTree;
    }
}
