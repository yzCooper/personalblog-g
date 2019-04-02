/*
* Copyright (c) 2017-2018 the hiteqinfo company.
*/

package com.yzblog.datacenter.web.cache;


import net.sf.ehcache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * ehcache缓存配置器
 *
 * @author yuzhou
 * @create 2017-10-20 13:17
 **/
@Configuration
public class EhCacheConfiguration {

    @Value("${ehcache.cacheConfigLocation}")
    private String cacheConfigLocation;

    /**
     * shiro ehcache manager
     *
     * @return {@link EhCacheManager}
     */
    @Bean
    public EhCacheManager getShiroEhCacheManager() {
        return new EhCacheManager();
    }

    /**
     * ehcache manager
     *
     * @return {@link CacheManager}
     */
    @Bean
    public CacheManager getEhCacheManager() {
        EhCacheManagerFactoryBean ehCacheManager = new EhCacheManagerFactoryBean();
        ehCacheManager.setConfigLocation(new ClassPathResource(cacheConfigLocation));

        // 初始化cacheManager
        ehCacheManager.afterPropertiesSet();
        return ehCacheManager.getObject();
    }
}
