package com.zhenghao.admin.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * 🙃
 * 🙃 log配置类
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2020/03/22 16:15
 * AuthApiProperties.java
 */
@ConfigurationProperties(prefix = "zh-admin.log")
public class LogProperties {

    /**
     * log directory
     * default value: log
     */
    private String directory = "log";

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }
}
