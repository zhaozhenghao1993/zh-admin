package net.zhenghao.zh.generator.core;

import net.zhenghao.zh.common.exception.BaseException;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

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
}
