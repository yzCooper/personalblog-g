/*
* Copyright (c) 2017-2018 the hiteqinfo company.
*/
package com.yzblog.datacenter.web.core.mybatis.query;


import java.io.Serializable;

/**
 * 过滤对象
 *
 * @author yuzhou
 * @create 2017-10-30 15:27
 **/
public class Filter implements Serializable {

    /**
     * 字段名称
     */
    private String name;

    /**
     * 值
     */
    private String values;

    /**
     * 值类型
     */
    private ValueType valueType;

    /**
     * 过滤表达式，默认like
     */
    private MatchType matchType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public ValueType getValueType() {
        return valueType;
    }

    public void setValueType(ValueType valueType) {
        this.valueType = valueType;
    }

    public MatchType getMatchType() {
        return matchType;
    }

    public void setMatchType(MatchType matchType) {
        this.matchType = matchType;
    }
}
