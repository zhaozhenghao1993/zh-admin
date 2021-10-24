package com.zhenghao.admin.common.constant;

/**
 * 系统级静态变量
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2017年12月6日 下午2:17:36
 * SystemConstants.java
 */
public class SystemConstants {

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
     * 验证码长度
     */
    public static final String CAPTCHA_CHAR_LENGTH = "4";

    /**
     * api 接口统一前缀
     */
    public static final String API_PREFIX = "/api/admin";

    public static final String SYS_SESSION_USER = "sys_user";

    public static final String SYS_SESSION_RSA_KEY = "sys_rsa_key";


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
     * 默认参数最大长度，超出就截取
     */
    public static final int DEFAULT_PARAM_STRING_MAX_LENGTH = 4950;

}
