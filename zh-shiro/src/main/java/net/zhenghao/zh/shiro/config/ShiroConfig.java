package net.zhenghao.zh.shiro.config;

import org.apache.shiro.config.Ini;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 🙃
 * 🙃 Shiro 配置类
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2018/12/13 22:42
 * ShiroConfig.java
 */

@Configuration
public class ShiroConfig {

    @Value("${spring.redis.shiro.host}")
    private String host;
    @Value("${spring.redis.shiro.port}")
    private int port;
    @Value("${spring.redis.shiro.timeout}")
    private int timeout;
    @Value("${spring.redis.shiro.password}")
    private String password;

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        System.out.println("ShiroConfiguration.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Ini ini = new Ini();
        Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        section.put("/sys/login", "anon");

        //所有资源的访问权限,必须放在最后
        section.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(section);
        return shiroFilterFactoryBean;
    }


}
