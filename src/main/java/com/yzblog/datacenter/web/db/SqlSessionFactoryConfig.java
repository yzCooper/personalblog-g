/*
 * Copyright (c) 2017-2018 the hiteqinfo company.
 */

package com.yzblog.datacenter.web.db;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * mybatis sqlsessionFactory
 *
 * @author yuzhou
 * @create 2017-10-19 15:11
 **/
@Configuration
@EnableTransactionManagement
@MapperScan("com.yzblog.datacenter.web.**.dao")
public class SqlSessionFactoryConfig implements TransactionManagementConfigurer {

    /**
     * mybatis 配置路径
     */
    @Value("${mybatis.config.location}")
    private String mybatisConfig;

    /**
     * mapper文件地址
     */
    @Value("${mybatis.mapping.locations}")
    private String mapperLocations;

    /**
     * 对象别名扫描
     */
    @Value("${mybatis.type.alias.package}")
    private String typeAliasPackage;

    /**
     * 系统数据源
     */
    private final DataSource dataSource;

    @Autowired
    public SqlSessionFactoryConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 创建sqlSessionFactoryBean 实例 并且设置configtion 如驼峰命名.等等 设置mapper 映射路径 设置datasource数据源
     *
     * @return SqlSessionFactoryBean
     * @throws Exception 数据库连接异常
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        // 设置数据连接池
        sqlSessionFactoryBean.setDataSource(dataSource);

        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(mybatisConfig));
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocations));
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasPackage);

        return sqlSessionFactoryBean;
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}