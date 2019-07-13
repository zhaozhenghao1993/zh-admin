package com.zhenghao.admin.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 🙃
 * 🙃 上传配置类
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/05/12 14:10
 * UploadsConfig.java
 */

@Configuration
public class UploadConfig implements WebMvcConfigurer {

    @Value("${zh-admin.file.uploads.folder}")
    private String folder;

    @Value("${zh-admin.file.uploads.path}")
    private String path;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(path + "**").addResourceLocations("file:" + folder);
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
