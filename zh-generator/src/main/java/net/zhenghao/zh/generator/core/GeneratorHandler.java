package net.zhenghao.zh.generator.core;

import net.zhenghao.zh.common.exception.BaseException;
import net.zhenghao.zh.common.util.DateUtils;
import net.zhenghao.zh.generator.entity.ColumnEntity;
import net.zhenghao.zh.generator.entity.GeneratorParamEntity;
import net.zhenghao.zh.generator.entity.TableEntity;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

    private GeneratorHandler() {
    }

    public static void generatorCode(TableEntity table, List<ColumnEntity> columns, GeneratorParamEntity params, ZipOutputStream zip) {
        //配置信息
        Configuration config = getConfig();
        //表名转换成Java类名
        String className = tableToJava(table.getTableName(), config.getString("tablePrefix"));// sys_user -> SysUser
        table.setClassName(className);//SysUser
        table.setObjName(StringUtils.uncapitalize(className));// sysUser

        //列信息
        for (ColumnEntity column : columns) {
            //列明转换，java属性名及对应方法名
            String columnName = columnToJava(column.getColumnName());// user_id -> UserId
            column.setFieldName(StringUtils.uncapitalize(columnName));// userId
            column.setMethodName(columnName);// UserId
            //列数据类型转换
            String fieldType = config.getString(column.getDataType(), "unknowType");
            column.setFieldType(fieldType);
            // 主键判断
            if ("PRI".equals(column.getColumnKey()) && table.getPk() == null) {
                table.setPk(column);
            }
        }
        table.setColumns(columns);

        // 没主键，则第一个字段为主键
        if (table.getPk() == null) {
            table.setPk(table.getColumns().get(0));
        }

        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);

        //封装模板数据
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", table.getTableName());
        map.put("comments", table.getTableComment());
        map.put("pk", table.getPk());
        map.put("className", table.getClassName());
        map.put("objName", table.getObjName());
        map.put("functionCode", params.getFunctionCode());
        map.put("functionMethod", WordUtils.capitalizeFully(params.getFunctionCode()));
        map.put("requestMapping", params.getRequestMapping());
        map.put("viewPath", params.getViewPath());
        map.put("authKey", urlToAuthKey(params.getRequestMapping()));
        map.put("columns", table.getColumns());
        map.put("package", config.getString("package"));
        map.put("module", params.getModule());
        map.put("author", config.getString("author"));
        map.put("email", config.getString("email"));
        map.put("datetime", DateUtils.dateTimeNow("yyyy/MM/dd HH:mm"));
        VelocityContext context = new VelocityContext(map);

        //获取模板列表
        List<String> templates = getTemplates();
        for (String template : templates) {
            try (StringWriter sw = new StringWriter()) {
                Template tpl = Velocity.getTemplate(template, "UTF-8");
                tpl.merge(context, sw);
                //添加zip
                if ("0".equals(params.getType())) {
                    zip.putNextEntry(new ZipEntry(getFileName(template, table.getClassName(), params.getModule(),
                            WordUtils.capitalizeFully(params.getFunctionCode()), config.getString("package"), params.getViewPath())));
                } else {
                    zip.putNextEntry(new ZipEntry(getFileName(template, table.getClassName(), params.getFunctionCode(), WordUtils.capitalizeFully(params.getFunctionCode()))));
                }
                IOUtils.write(sw.toString(), zip, "UTF-8");
                zip.closeEntry();
            } catch (IOException e) {
                throw new BaseException("渲染模板失败，表名：" + table.getTableName(), e);
            }
        }
    }

    private static List<String> getTemplates() {
        List<String> templates = new ArrayList<>();
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
     *
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
     *
     * @param columnName
     * @return
     */
    public static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
    }

    /**
     * 权限标识
     *
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
     * @param className      SysUser
     * @param functionCode   user
     * @param functionMethod User
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
    public static String getFileName(String template, String className, String module,
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
            return webPath + "api" + File.separator + viewPath + ".js";
        }

        if (template.contains("Modal.vue.vm")) {
            return webPath + "view" + File.separator + viewPath + File.separator + functionMethod + "Modal.vue";
        }

        if (template.contains("List.vue.vm")) {
            return webPath + "view" + File.separator + viewPath + File.separator + functionMethod + "List.vue";
        }

        return null;
    }
}
