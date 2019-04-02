package com.yzblog.datacenter.web.modules.security;

import com.yzblog.datacenter.web.core.exception.EFWebException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置对象
 *
 * @author yuzhou
 * @create 2017-10-21 12:11
 **/
@Component
@ConfigurationProperties("security.shiro")
public class ShiroProperties {

    /**
     * 登录页面
     */
    private String loginUrl;

    /**
     * 登录成功访问页面
     */
    private String successUrl;

    /**
     * 没有权限跳转页面
     */
    private String unauthorizedUrl;

    /**
     * 过滤规则
     */
    private String[] filterChainDefinitions;

    /**
     * 过滤器
     * key:过滤名称
     * value:spring bean name
     */
    private Map<String, String> filters = new HashMap<>();

    /**
     * 将filterChainDefinitions转换成linkedMap
     *
     * @return LinkedHashMap
     */
    public LinkedHashMap<String, String> convertFilterChainDefinitionsToLinkedMap() {
        if (filterChainDefinitions == null) {
            return null;
        }

        // LinkedHashMap有序的过滤链
        LinkedHashMap<String, String> linkedFilterChainDefinition = new LinkedHashMap<>();
        for (String fileterChianDefinition : filterChainDefinitions) {
            String[] filterChainArray = fileterChianDefinition.split("=");
            if (filterChainArray.length != 2) {
                throw new EFWebException("fileterChianDefinition text format error ,fileterChianDefinition: " + fileterChianDefinition);
            }

            String filterRuleUrl = filterChainArray[0].trim();
            String filterRuleName = filterChainArray[1].trim();

            linkedFilterChainDefinition.put(filterRuleUrl, filterRuleName);
        }

        return linkedFilterChainDefinition;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getUnauthorizedUrl() {
        return unauthorizedUrl;
    }

    public void setUnauthorizedUrl(String unauthorizedUrl) {
        this.unauthorizedUrl = unauthorizedUrl;
    }

    public String[] getFilterChainDefinitions() {
        return filterChainDefinitions;
    }

    public void setFilterChainDefinitions(String[] filterChainDefinitions) {
        this.filterChainDefinitions = filterChainDefinitions;
    }

    public Map<String, String> getFilters() {
        return filters;
    }

    public void setFilters(Map<String, String> filters) {
        this.filters = filters;
    }
}
