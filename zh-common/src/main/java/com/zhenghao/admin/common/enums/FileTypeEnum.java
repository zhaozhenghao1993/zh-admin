package com.zhenghao.admin.common.enums;

/**
 * 文件类型
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2018年2月9日 上午11:17:36
 * FileType.java
 */
public enum FileTypeEnum {

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

    private FileTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
