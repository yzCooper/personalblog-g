package com.yzblog.datacenter.web.core.mybatis.query;


/**
 * 匹配类型
 *
 * @author yuzhou
 * @create 2017-10-30 15:22:08
 */
public enum MatchType {

    /**
     * 等于
     */
    EQ("="),

    /**
     * 不等于
     */
    NEQ("<>"),

    /**
     * 大于
     */
    GT(">"),

    /**
     * 小于
     */
    LT("<"),
    /**
     * 大于等于
     */
    GTE(">="),

    /**
     * 小于等于
     */
    LTE("<="),

    /**
     * like 查询
     */
    LIKE("like");

    private String value;

    MatchType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
