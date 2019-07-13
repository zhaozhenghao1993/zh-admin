package com.zhenghao.admin.generator.entity;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 🙃
 * 🙃 代码生成器请求参数
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/04/20 15:43
 * GeneratorParamEntity.java
 */

public class GeneratorParamEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表名
     */
    @NotBlank(message = "表名不能为空 !")
    private String tableName;

    /**
     * 系统模块，权限管理auth
     */
    @NotBlank(message = "系统模块不能为空 !")
    private String module;

    /**
     * 功能编码，用户管理user
     */
    @NotBlank(message = "功能编码不能为空 !")
    private String functionCode;

    /**
     * 后台请求地址，用户管理sys/user
     */
    @NotBlank(message = "后台请求地址不能为空 !")
    private String requestMapping;

    /**
     * 页面路径，用户管理system/user
     */
    @NotBlank(message = "页面路径不能为空 !")
    private String viewPath;

    /**
     * 生成类型，0：生成包结构，1：只生成源代码
     */
    @NotBlank(message = "生成类型不能为空 !")
    private String type;

    public GeneratorParamEntity() {
        super();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public String getRequestMapping() {
        return requestMapping;
    }

    public void setRequestMapping(String requestMapping) {
        this.requestMapping = requestMapping;
    }

    public String getViewPath() {
        return viewPath;
    }

    public void setViewPath(String viewPath) {
        this.viewPath = viewPath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "GeneratorParamEntity{" +
                "tableName='" + tableName + '\'' +
                ", module='" + module + '\'' +
                ", functionCode='" + functionCode + '\'' +
                ", requestMapping='" + requestMapping + '\'' +
                ", viewPath='" + viewPath + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
