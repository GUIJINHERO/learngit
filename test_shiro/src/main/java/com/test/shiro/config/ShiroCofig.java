package com.test.shiro.config;

import com.test.shiro.constants.ShiroConstants;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import java.util.Map;

@Configuration
public class ShiroCofig {

    @Autowired
    private Environment environment;

    private String redisHost;

    private String redisPort;

    private String userName;

    private Integer dataBase = 0;

    private Integer timeOut;

    private String pswd;

    @PostConstruct
    public void init(){
        this.redisHost = environment.getProperty("redis.host");
        this.redisPort = environment.getProperty("redis.port");
        this.userName = environment.getProperty("redis.userName");
        this.pswd = environment.getProperty("redis.pswd");
        this.dataBase =  Integer.valueOf(environment.getProperty("redis.dataBase"));
        this.timeOut = Integer.valueOf(environment.getProperty("redis.timeOut"));
    }



    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager());
        securityManager.setCacheManager(cacheManager());
        securityManager.setRealm(realm());
        return securityManager;
    }


    @Bean
    public ShiroFilterFactoryBean ShiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);

        Map<String, String> filterChainDefinitionMap = filterFactoryBean.getFilterChainDefinitionMap();

        filterChainDefinitionMap.put("/user/login","anon");
        filterChainDefinitionMap.put("/*","authc");

        Map<String, Filter> filters = filterFactoryBean.getFilters();
        filters.put("authc",shiroFormAuthenFilter());

        filterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        filterFactoryBean.setFilters(filters);

        return filterFactoryBean;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(ShiroConstants.MD5);
        hashedCredentialsMatcher.setHashIterations(ShiroConstants.HASH_ITERATIONS);
        return hashedCredentialsMatcher;
    }

    @Bean
    public Realm realm(){
        ShiroRealmConfig realm = new ShiroRealmConfig();
        realm.setCredentialsMatcher(hashedCredentialsMatcher());
        return realm;
    }

    @Bean
    public SessionManager sessionManager(){
        ShiroSessionManagerConfig sessionManagerConfig = new ShiroSessionManagerConfig();
        sessionManagerConfig.setSessionDAO(redisSessionDAO());
        return sessionManagerConfig;
    }

    @Bean
    public RedisCacheManager cacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return  redisCacheManager;
    }

    @Bean
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    @Bean
    public RedisManager redisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(this.redisHost+":"+this.redisPort);
        redisManager.setPassword(this.pswd);
        redisManager.setDatabase(this.dataBase);
        redisManager.setTimeout(this.timeOut);
        return  redisManager;
    }


    @Bean
    public ShiroFormAuthenFilter shiroFormAuthenFilter(){
        return  new ShiroFormAuthenFilter();
    }

    @Bean("lifecycleBeanPostProcessor")
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
       return  new LifecycleBeanPostProcessor();
    }


}
