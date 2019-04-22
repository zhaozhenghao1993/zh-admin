package net.zhenghao.zh.generator.core;

import net.zhenghao.zh.common.exception.BaseException;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 🙃
 * 🙃 代码生成器处理类
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/04/21 22:10
 * GeneratorHandler.java
 */

public class GeneratorHandler {

    private GeneratorHandler() {}

    private static List<String> getTemplates() {
        List<String> templates = new ArrayList<String>();
        templates.add("template/Entity.java.vm");
        templates.add("template/Mapper.java.vm");
        templates.add("template/Mapper.xml.vm");
        templates.add("template/Service.java.vm");
        templates.add("template/ServiceImpl.java.vm");
        templates.add("template/Controller.java.vm");
        templates.add("template/Modal.vue.vm");
        templates.add("template/List.vue.vm");
        templates.add("template/api.js.vm");
        return templates;
    }

    public static Configuration getConfig() {
        Configurations configs = new Configurations();
        Configuration config = null;
        try {
            config = configs.properties(new File("generator.properties"));
        } catch (ConfigurationException e) {
            throw new BaseException("获取代码生成器配置文件失败，", e);
        }
        return config;
    }

    /**
     * 表名转换成Java类名
     * @param tableName
     * @param tablePrefix
     * @return
     */
    public static String tableToJava(String tableName, String tablePrefix) {
        if (StringUtils.isNotBlank(tablePrefix)) {
            tableName = tableName.replace(tablePrefix, "");
        }
        return columnToJava(tableName);
    }

    /**
     * 列名转换成Java属性名
     * @param columnName
     * @return
     */
    public static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[] { '_' }).replace("_", "");
    }

    /**
     * 权限标识
     * @param url
     * @return
     */
    public static String urlToAuthKey(String url) {
        return url.replace("/", ":");
    }

    /**
     * 获取文件名，不带包名
     *
     * @param template
     * @param className         SysUser
     * @param functionCode      user
     * @param functionMethod    User
     * @return
     */
    public static String getFileName(String template, String className, String functionCode, String functionMethod) {
        String packagePath = "java" + File.separator;
        if (template.contains("Entity.java.vm")) {
            return packagePath + className + "Entity.java";
        }

        if (template.contains("Mapper.java.vm")) {
            return packagePath + className + "Mapper.java";
        }

        if (template.contains("Mapper.xml.vm")) {
            return packagePath + className + "Mapper.xml";
        }

        if (template.contains("Service.java.vm")) {
            return packagePath + className + "Service.java";
        }

        if (template.contains("ServiceImpl.java.vm")) {
            return packagePath + className + "ServiceImpl.java";
        }

        if (template.contains("Controller.java.vm")) {
            return packagePath + className + "Controller.java";
        }

        if (template.contains("api.js.vm")) {
            return "web" + File.separator + functionCode + ".js";
        }

        if (template.contains("Modal.vue.vm")) {
            return "web" + File.separator + functionMethod + "Modal.vue";
        }

        if (template.contains("List.vue.vm")) {
            return "web" + File.separator + functionMethod + "List.vue";
        }

        return null;
    }

    /**
     * 获取文件名，带包名
     */
    public static String getFileName(String template, String className, String module, String functionCode,
                                     String functionMethod, String packageName, String viewPath) {
        String packagePath = "java" + File.separator;
        String webPath = "vue" + File.separator;
        if (StringUtils.isNotBlank(packageName)) {
            packagePath += packageName.replace(".", File.separator) + File.separator + module + File.separator;
        }

        if (template.contains("Entity.java.vm")) {
            return packagePath + "entity" + File.separator + className + "Entity.java";
        }

        if (template.contains("Mapper.java.vm")) {
            return packagePath + "dao" + File.separator + className + "Mapper.java";
        }

        if (template.contains("Mapper.xml.vm")) {
            return packagePath + "dao" + File.separator + className + "Mapper.xml";
        }

        if (template.contains("Service.java.vm")) {
            return packagePath + "service" + File.separator + className + "Service.java";
        }

        if (template.contains("ServiceImpl.java.vm")) {
            return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
        }

        if (template.contains("Controller.java.vm")) {
            return packagePath + "controller" + File.separator + className + "Controller.java";
        }

        if (template.contains("api.js.vm")) {
            return webPath + "api" + File.separator + viewPath + File.separator + ".js";
        }

        if (template.contains("Modal.vue.vm")) {
            return webPath + "view" + File.separator + viewPath + File.separator + functionMethod + "Modal.vue";
        }

        if (template.contains("edit.html.vm")) {
            return webPath + "view" + File.separator + viewPath + File.separator + functionMethod + "List.vue";
        }

        return null;
    }
}
