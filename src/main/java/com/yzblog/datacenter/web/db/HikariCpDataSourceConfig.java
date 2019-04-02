/*
 * Copyright (c) 2017-2018 the hiteqinfo company.
 */

package com.yzblog.datacenter.web.db;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 创建Druid数据库连接池
 *
 * @author yuzhou
 * @create 2017-10-19 15:11
 **/
@Configuration
public class HikariCpDataSourceConfig {

    /**
     * 数据库连接池name
     */
    public static final String DATASOURCE_NAME = "default_ds";
    public static final String DATASOURCE_NAME_MYSQL = "mysql";

    /**
     * 根据配置创建DataSource对象
     *
     * @return DruidDataSource
     * @throws SQLException filters传入有误
     */
    @Bean(DATASOURCE_NAME)
    @Primary
    public DataSource dataSource() throws SQLException, ClassNotFoundException {
        //按照目标数据源名称和目标数据源对象的映射存放在Map中
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DATASOURCE_NAME_MYSQL, dataSourceMysql());
        //采用是想AbstractRoutingDataSource的对象包装多数据源
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);
        //设置默认的数据源，当拿不到数据源时，使用此配置
        dataSource.setDefaultTargetDataSource(dataSourceMysql());
        return dataSource;
    }


    /**
     * 根据配置创建DataSource对象
     *
     * @return DruidDataSource
     * @throws SQLException filters传入有误
     */
    @Bean(DATASOURCE_NAME_MYSQL)
    @ConfigurationProperties(prefix = "mysql")
    public DataSource dataSourceMysql() throws SQLException, ClassNotFoundException {
        // 创建数据库连接池
        return new HikariDataSource();
    }

}