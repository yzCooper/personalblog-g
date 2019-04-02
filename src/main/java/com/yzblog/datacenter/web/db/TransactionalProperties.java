package com.yzblog.datacenter.web.db;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 事务参数配置
 *
 * @author yuzhou
 * @create 2017-10-21 12:28
 **/
@Component
@ConfigurationProperties(prefix = "server.transactional")
public class TransactionalProperties {
    /**
     * 自定义事务 BeanName 拦截
     */
    private String[] customizeTransactionBeanNames = new String[]{};
    /**
     * 自定义方法名的事务属性相关联,可以使用通配符(*)字符关联相同的事务属性的设置方法; 只读事务
     */
    private String[] customizeReadOnlyMethodRuleTransactionAttributes = {};
    /**
     * 自定义方法名的事务属性相关联,可以使用通配符(*)字符关联相同的事务属性的设置方法;
     * 传播事务(默认的){@link org.springframework.transaction.annotation.Propagation#REQUIRED}
     */
    private String[] customizeRequiredMethodRuleTransactionAttributes = {};

    public String[] getCustomizeTransactionBeanNames() {
        return customizeTransactionBeanNames;
    }

    public void setCustomizeTransactionBeanNames(String[] customizeTransactionBeanNames) {
        this.customizeTransactionBeanNames = customizeTransactionBeanNames;
    }

    public String[] getCustomizeReadOnlyMethodRuleTransactionAttributes() {
        return customizeReadOnlyMethodRuleTransactionAttributes;
    }

    public void setCustomizeReadOnlyMethodRuleTransactionAttributes(String[] customizeReadOnlyMethodRuleTransactionAttributes) {
        this.customizeReadOnlyMethodRuleTransactionAttributes = customizeReadOnlyMethodRuleTransactionAttributes;
    }

    public String[] getCustomizeRequiredMethodRuleTransactionAttributes() {
        return customizeRequiredMethodRuleTransactionAttributes;
    }

    public void setCustomizeRequiredMethodRuleTransactionAttributes(String[] customizeRequiredMethodRuleTransactionAttributes) {
        this.customizeRequiredMethodRuleTransactionAttributes = customizeRequiredMethodRuleTransactionAttributes;
    }
}