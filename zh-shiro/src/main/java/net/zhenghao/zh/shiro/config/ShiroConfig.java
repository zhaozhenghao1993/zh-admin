package net.zhenghao.zh.shiro.config;

import net.zhenghao.zh.shiro.filter.ShiroLoginFilter;
import net.zhenghao.zh.shiro.filter.ShiroPermsFilter;
import net.zhenghao.zh.shiro.security.UserRealm;
import net.zhenghao.zh.shiro.session.UserSessionManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.config.Ini;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

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
    @Value("${spring.redis.shiro.timeout}")
    private int timeout;
    @Value("${spring.redis.shiro.password}")
    private String password;

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        //登陆失败过滤器
        filters.put("authc", new ShiroLoginFilter());
        filters.put("perms", new ShiroPermsFilter());

        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/sys/login", "anon");
        filterChainDefinitionMap.put("/sys/test", "perms[test]");
        filterChainDefinitionMap.put("/sys/user/save", "perms[sys:user:save]");

        //所有资源的访问权限,必须放在最后
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        return userRealm;
    }

    @Bean
    public SecurityManager securityManager(SessionManager sessionManager, RedisCacheManager cacheManager, UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        //自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager);
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(cacheManager);
        return securityManager;
    }

    //自定义sessionManager
    @Bean
    public SessionManager sessionManager(RedisSessionDAO redisSessionDAO) {
        UserSessionManager userSessionManager = new UserSessionManager();
        userSessionManager.setSessionDAO(redisSessionDAO);
        return userSessionManager;
    }

    /**
     * 配置shiro redisManager
     * <p>
     * 使用的是shiro-redis开源插件
     * @return
     */
    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setTimeout(timeout);
        redisManager.setPassword(password);
        return redisManager;
    }

    @Bean
    public RedisCacheManager cacheManager(RedisManager redisManager) {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager);
        //shiro-redis 需要一个id字段来标识Redis中的授权对象。因此，请确保您的主要类有一个字段，您可以获得此对象的唯一ID
        redisCacheManager.setPrincipalIdFieldName("userId");
        return redisCacheManager;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * <p>
     * 使用的是shiro-redis开源插件
     * @return
     */
    @Bean
    public RedisSessionDAO redisSessionDAO(RedisManager redisManager) {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager);
        return redisSessionDAO;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式,所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    //自动创建代理，没有这个鉴权可能会出错
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        autoProxyCreator.setProxyTargetClass(true);
        return autoProxyCreator;
    }

}
