package com.zhenghao.admin.common.constant;

/**
 * 系统提示静态变量
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2017年11月22日 下午2:47:13
 * MsgConstant.java
 */
public class MsgConstants {

    private MsgConstants() {
    }

    /**
     * 操作成功
     */
    public static final String MSG_OPERATION_SUCCESS = "操作成功";

    /**
     * 操作失败
     */
    public static final String MSG_OPERATION_FAILED = "操作失败";

    /**
     * 删除时，提示有子节点无法删除
     */
    public static final String MSG_HAS_CHILD = "操作失败,当前所选数据有子节点数据!";

    /**
     * 加载表单数据错误提示
     */
    public static final String MSG_INIT_FORM = "初始化表单数据失败,请重试!";

    /**
     * 删除数据项不是全部所选
     *
     * @param total
     * @param process
     * @return
     */
    public static String removeFailed(int total, int process) {
        return "本次共处理:" + total + "条,成功:" + process + "条!";
    }
}
