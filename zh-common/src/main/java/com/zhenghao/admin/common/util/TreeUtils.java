package com.zhenghao.admin.common.util;

import com.zhenghao.admin.common.constant.SystemConstant;
import com.zhenghao.admin.common.entity.TreeNode;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 🙃
 * 🙃 将 List<TreeNode> 转成 tree 格式
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
     *
     * @param trees    传入的树节点列表
     * @param parentId 根节点的parentId
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
     * @param trees      传入的树节点列表
     * @param parentTree 当前父节点
     * @return
     */
    private static <T extends TreeNode> T getTree(List<T> trees, T parentTree) {
        List<T> childList = trees.stream().filter(tree -> parentTree.getId().equals(tree.getParentId())).collect(Collectors.toList());
        if (!childList.isEmpty()) {
            childList.forEach(child -> parentTree.addChild(getTree(trees, child)));
        }
        return parentTree;
    }

    /**
     * 检查构建的 Tree 是否存在循环依赖
     * 如果新选择的上级的 ancestors 属性发现其中含有
     * 被编辑 Tree 的 id，或者选择了他自己本身
     * 则认为存在循环嵌套发生
     *
     * @param parentTreeNode 新选择的 tree 父节点
     * @param treeNode tree 节点
     * @return true 则发生循环依赖; false 未发生循环依赖
     */
    public static boolean isTreeCircularReference(TreeNode parentTreeNode, @NotNull TreeNode treeNode) {
        if (parentTreeNode == null) {
            return false;
        }
        if (parentTreeNode.getAncestors() == null
                || parentTreeNode.getId() == null
                || parentTreeNode.getParentId() == null) {
            return false;
        }
        return Arrays.stream(parentTreeNode.getAncestors().split(SystemConstant.TREE_ANCESTORS_SPLIT))
                .anyMatch(ancestorsId -> ancestorsId.equals(treeNode.getId() + ""))
                || treeNode.getParentId().equals(treeNode.getId());
    }

    /**
     * 构建祖级列表
     *
     * @param parentTreeNode 父节点
     * @param treeNode 被构建节点
     * @return 祖级列表
     */
    public static String buildAncestors(TreeNode parentTreeNode, @NotNull TreeNode treeNode) {
        String ancestors;
        if (parentTreeNode == null) {
            ancestors = treeNode.getParentId() + "";
        } else {
            ancestors = parentTreeNode.getAncestors() + SystemConstant.TREE_ANCESTORS_SPLIT + parentTreeNode.getId();
        }
        return ancestors;
    }
}
