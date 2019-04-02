package com.yzblog.datacenter.web.core.mybatis.query;

/**
 * 值类型
 *
 * @author yuzhou
 * @create 2017-10-30 15:22:08
 */
public enum ValueType {

    /**
     * 数值类型，如：2017
     */
    NUMBER,

    /**
     * 字符串，如：abc
     */
    STRING,

    /**
     * 表达式，如：concat('a','b')
     */
    EXPRESSION;


}
