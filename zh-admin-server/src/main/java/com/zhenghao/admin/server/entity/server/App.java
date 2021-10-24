package com.zhenghao.admin.server.entity.server;

/**
 * 🙃
 * 🙃 App项目应用 信息
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/04/06 22:36
 * App.java
 */

public class App {

    /**
     * 项目路径
     */
    private String appDir;

    /**
     * 日志存放路径
     */
    private String logDir;

    /**
     * 上传文件路径
     */
    private String uploadDir;

    public String getAppDir() {
        return appDir;
    }

    public void setAppDir(String appDir) {
        this.appDir = appDir;
    }

    public String getLogDir() {
        return logDir;
    }

    public void setLogDir(String logDir) {
        this.logDir = logDir;
    }

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
