package com.zhenghao.admin.common.constant;

/**
 * 系统级静态变量
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2017年12月6日 下午2:17:36
 * SystemConstant.java
 */
public class SystemConstant {

    /**
     * 超级管理员ID
     */
    public static final Long SUPER_ADMIN = 1L;

    /**
     * 超级管理员角色ID
     */
    public static final Long SUPER_ADMIN_ROLE = 1L;

    /**
     * 默认树根节点
     */
    public static final Long TREE_ROOT = 0L;

    /**
     * 默认树祖籍 id 分隔符
     */
    public static final String TREE_ANCESTORS_SPLIT = ",";

    /**
     * api 接口统一前缀
     */
    public static final String API_PREFIX = "/api";


    /**
     * 当前用户ID key
     */
    public static final String CONTEXT_KEY_USER_ID = "currentUserId";

    /**
     * 当前用户username key
     */
    public static final String CONTEXT_KEY_USERNAME = "currentUserName";

    /**
     * 当前用户name key
     */
    public static final String CONTEXT_KEY_NAME = "currentName";

    /**
     * 当前用户token key
     */
    public static final String CONTEXT_KEY_USER_TOKEN = "currentUserToken";

    /**
     * JWT userId标识
     */
    public static final String JWT_KEY_USER_ID = "userId";

    /**
     * JWT username标识
     */
    public static final String JWT_KEY_USERNAME = "username";

    /**
     * JWT name标识
     */
    public static final String JWT_KEY_NAME = "name";

    /**
     * 默认备注最大长度，超出就截取
     */
    public static final int DEFAULT_REMARK_STRING_MAX_LENGTH = 450;

    /**
     * 日志类型
     */
    public enum LogType {

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

        private LogType(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

    }

    /**
     * 通用变量,表示 可用、禁用、显示、隐藏
     *
     * @author:zhaozhenghao
     * @Email :736720794@qq.com
     * @date :2017年12月6日 下午2:17:36
     * SystemConstant.java
     */
    public enum StatusType {

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

        private StatusType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 文件类型
     *
     * @author:zhaozhenghao
     * @Email :736720794@qq.com
     * @date :2018年2月9日 上午11:17:36
     * FileType.java
     */
    public enum FileType {
        /**
         * 图片
         */
        IMAGE(1),
        /**
         * 文档
         */
        DOCUMENT(2),
        /**
         * 视频
         */
        VIDEO(3),
        /**
         * 种子
         */
        SEED(4),
        /**
         * 音乐
         */
        MUSIC(5),
        /**
         * 其他
         */
        OTHER(6);

        private int value;

        private FileType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
