package com.yzblog.datacenter.web.core.mybatis.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 分页类
 *
 * @author yuzhou
 * @create 2017-10-30 15:58:52
 */
public class Page<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6409860586349176160L;

    /**
     * 每页查询个数
     */
    private int limit;

    /**
     * 偏移位置
     */
    private int offset;

    /**
     * 总记录树
     */
    private Long total = 0L;

    /**
     * 结果集
     */
    private List<T> rows = new ArrayList<T>();

    /**
     * 排序字段
     */
    private String order;

    /**
     * 排序规则
     */
    private String sort;

    /**
     * 参数map，mybatis中使用样例：#{page.parameter.xx}
     */
    private Map<String, Object> parameter;

    /**
     * 过滤信息，用于高级过滤查询
     */
    private List<Filter> filters = new ArrayList<>();

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Map<String, Object> getParameter() {
        return parameter;
    }

    public void setParameter(Map<String, Object> parameter) {
        this.parameter = parameter;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public Page(int limit, int offset, String order) {
        this.limit = limit;
        this.offset = offset;
        this.order = order;
    }

    public Page() {
    }

}
