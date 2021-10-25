package com.zhenghao.admin.common.constant;

/**
 * 🙃
 * 🙃 文件上传静态变量
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/05/13 22:48
 * UploadConstants.java
 */

public class UploadConstants {

    private UploadConstants() {
    }

    public static final String PATH_SEPARATOR = "/";

    public static final String DEFAULT_PATH = "/uploads/";

    public static final String DEFAULT_UPLOAD_ROOT_DIRECTORY = "uploads";

    /**
     * 用户头像上传路径
     */
    public static final String USER_AVATAR_DIRECTORY = "system/user/avatar/";

    /**
     * 用户头像默认地址
     */
    public static final String USER_AVATAR_DEFAULT_PATH = "/avatar.png";

    /**
     * 用户头像文件名
     */
    public static final String USER_AVATAR_FILE_NAME = "avatar";

    public static final Integer USER_AVATAR_FILE_SIZE = 1;

    public static final String USER_AVATAR_FILE_SIZE_UNIT = "M";
}
