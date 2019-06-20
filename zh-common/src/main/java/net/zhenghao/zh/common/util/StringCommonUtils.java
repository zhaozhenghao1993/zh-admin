package net.zhenghao.zh.common.util;

/**
 * 🙃
 * 🙃 ZH String工具类，换个名字和lang3StringUtils区分开
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/01/18 23:13
 * StringCommonUtils.java
 */

public class StringCommonUtils {

    private StringCommonUtils() {
    }

    public static String getObjectValue(Object obj){
        return obj==null ? "" : obj.toString();
    }
}
