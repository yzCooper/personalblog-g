/*
* Copyright (c) 2017-2018 the hiteqinfo company.
*/
package com.yzblog.datacenter.web.core.mybatis.interceptor;

/**
 * 分页帮助方言
 *
 * @author yuzhou
 * @create 2017-10-30 14:27
 **/
public enum PageHelperDialect {

    /**
     * db2数据库
     */
    DB2("db2"),

    /**
     * derby数据库
     */
    DERBY("derby"),

    /**
     * h2数据库
     */
    H2("h2"),

    /**
     * hsql数据库
     */
    HSQL("hsql"),

    /**
     * mysql数据库
     */
    MYSQL("mysql"),

    /**
     * oracle数据库
     */
    ORACLE("oracle"),

    /**
     * postgre数据库
     */
    POSTGRE("postgre"),

    /**
     * mssql数据库
     */
    MSSQL("mssql"),

    /**
     * sqlserver 数据库
     */
    SQLSERVER("sqlserver"),

    /**
     * sybase数据库
     */
    SYBASE("sybase");

    /**
     * 数据库标识值
     */
    private String values;

    PageHelperDialect(String values) {
        this.values = values;
    }

    public String getValues() {
        return values;
    }
}
