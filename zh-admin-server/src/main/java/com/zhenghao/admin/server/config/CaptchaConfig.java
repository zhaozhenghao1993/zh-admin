package com.zhenghao.admin.server.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.zhenghao.admin.common.constant.SystemConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 🙃
 * 🙃
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2020/05/22 20:10
 * CaptchaConfig.java
 */
@Configuration
public class CaptchaConfig {

    @Bean
    public DefaultKaptcha producer() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", "no");
        properties.setProperty("kaptcha.textproducer.font.color", "black");
        properties.setProperty("kaptcha.image.width", "100");
        properties.setProperty("kaptcha.image.height", "50");
        properties.setProperty("kaptcha.session.key", "code");
        properties.setProperty("kaptcha.textproducer.char.length", SystemConstants.CAPTCHA_CHAR_LENGTH);
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
