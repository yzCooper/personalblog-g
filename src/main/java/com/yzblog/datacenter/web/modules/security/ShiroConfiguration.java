/*
* Copyright (c) 2017-2018 the hiteqinfo company.
*/
package com.yzblog.datacenter.web.modules.security;



import com.yzblog.datacenter.web.modules.sys.service.UserService;
import org.apache.commons.collections.MapUtils;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.BeansException;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * Shiro权限框架配置
 *
 * @author yuzhou
 * @create 2017-10-20 11:00
 **/
@Configuration
public class ShiroConfiguration implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        return filterRegistration;
    }

    @Bean(name = "shiroAuthorizingRealm")
    public ShiroAuthorizingRealm myShiroRealm(EhCacheManager cacheManager, UserService userSerice) {
        ShiroAuthorizingRealm realm = new ShiroAuthorizingRealm(userSerice);
        realm.setCacheManager(cacheManager);
        return realm;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(EhCacheManager cacheManager, ShiroAuthorizingRealm shiroAuthorizingRealm) {
        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
        dwsm.setRealm(shiroAuthorizingRealm);

        //  用户授权/认证信息Cache, 采用EhCache 缓存
        dwsm.setCacheManager(cacheManager);
        return dwsm;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        // 启用支持shiro注解
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
    }

    /**
     * ShiroFilter<br/>
     * 注意这里参数中的 StudentService 和 IScoreDao 只是一个例子，因为我们在这里可以用这样的方式获取到相关访问数据库的对象，
     * 然后读取数据库相关配置，配置到 shiroFilterFactoryBean 的访问规则中。实际项目中，请使用自己的Service来处理业务逻辑。
     *
     * @author yuzhou
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager, ShiroProperties shiroProperties) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl(shiroProperties.getLoginUrl());
        // 登录成功后要跳转的连接
        shiroFilterFactoryBean.setSuccessUrl(shiroProperties.getSuccessUrl());
        // 没有权限跳转页面
        shiroFilterFactoryBean.setUnauthorizedUrl(shiroProperties.getUnauthorizedUrl());

        // 自定义过滤器
        loadShiroFilter(shiroFilterFactoryBean, shiroProperties);

        // 过滤规则
        shiroFilterFactoryBean.setFilterChainDefinitionMap(shiroProperties.convertFilterChainDefinitionsToLinkedMap());
        return shiroFilterFactoryBean;
    }

    /**
     * 加载shiro过滤器
     *
     * @param shiroFilterFactoryBean shiroFilterFactoryBean
     */
    private void loadShiroFilter(ShiroFilterFactoryBean shiroFilterFactoryBean, ShiroProperties shiroProperties) {
        Map<String, String> filter = shiroProperties.getFilters();
        if (MapUtils.isEmpty(filter)) {
            return;
        }

        Map<String, Filter> shiroFilter = new HashMap<>(filter.size());

        for (Map.Entry<String, String> entry : filter.entrySet()) {
            String springBeanName = entry.getValue();
            Filter filterBean = applicationContext.getBean(springBeanName, Filter.class);

            shiroFilter.put(entry.getKey(), filterBean);
        }

        // 设置自定义过滤器
        shiroFilterFactoryBean.setFilters(shiroFilter);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
