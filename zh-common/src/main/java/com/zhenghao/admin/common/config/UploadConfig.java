package com.zhenghao.admin.common.config;

import com.zhenghao.admin.common.constant.UploadConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import java.io.File;

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

    @Value("${zh-admin.file.uploads.directory:/data/app/zh-admin/}")
    private String directory;

    /**
     * 保证上传的真实路径为 {directory}/uploads/
     */
    private String uploadDirectory;

    @PostConstruct
    public void init() {
        if (directory.endsWith(File.separator) || directory.endsWith("/") || directory.endsWith("\\\\")) {
            directory = directory + File.separator;
        }
        this.uploadDirectory = directory + UploadConstants.DEFAULT_UPLOAD_ROOT_DIRECTORY + File.separator;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(UploadConstants.DEFAULT_PATH + "**").addResourceLocations("file:" + uploadDirectory);
    }

    /**
     * 获取上传文件真实目录
     * eg: 如果是目录  则返回 /upload/test
     * @param file
     * @return
     */
    public String getUploadFileDirectory(String file) {
        if (file.startsWith(File.separator) || directory.endsWith("/")) {
            file = file.substring(1);
        } else if (file.startsWith("\\\\")) {
            file = file.substring(2);
        }
        return uploadDirectory.concat(file);
    }

    public String getUploadFilePath(String file) {
        if (file.contains("\\\\")) {
            file = file.replace("\\\\", UploadConstants.PATH_SEPARATOR);
        }
        if (file.startsWith(UploadConstants.PATH_SEPARATOR)) {
            file = file.substring(1);
        }
        return UploadConstants.DEFAULT_PATH.concat(file);
    }


    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }
}
