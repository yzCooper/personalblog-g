package com.yzblog.datacenter.web.core.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yzblog.datacenter.web.core.exception.EFWebException;
import com.yzblog.datacenter.web.core.mybatis.query.Filter;
import com.yzblog.datacenter.web.core.mybatis.query.Page;
import com.yzblog.datacenter.web.core.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 分页查询控制器
 * Created by hua on 2017/8/1.
 */
public class PagableQueryController extends BaseController {

    /**
     * 数值正则表达式
     */
    private static final String NUMBER_REGEX = "\\d+";

    /**
     * 每页查询个数
     */
    public static final String LIMIT = "limit";

    /**
     * 偏移位置
     */
    public static final String OFFSET = "offset";

    /**
     * 排序字段
     */
    public static final String SORT = "sort";

    /**
     * 排序规则
     */
    public static final String ORDER = "order";

    /**
     * 过滤信息
     */
    public static final String FILTERS = "filters";

    /**
     * 参数
     */
    public static final String PARAMETER = "parameter";


    /**
     * 创建分页对象
     *
     * @param request request
     * @return Page
     */
    protected <T> Page<T> getPage(HttpServletRequest request) {
        Page<T> page = new Page<>();
        page.setLimit(parseInt(request.getParameter(LIMIT)));
        page.setOffset(parseInt(request.getParameter(OFFSET)));
        page.setSort(request.getParameter(SORT));
        page.setOrder(request.getParameter(ORDER));

        // 设置过滤配置信息
        setFilter(request, page);
        // 设置参数
        setParameter(request, page);

        return page;
    }

    /**
     * 设置过滤配置信息
     *
     * @param request http servlet request
     * @param page    分页对象
     */
    private <T> void setFilter(HttpServletRequest request, Page<T> page) {
        String queryParams = request.getParameter(FILTERS);
        if (StringUtils.isBlank(queryParams)) {
            return;
        }

        try {
            page.setFilters(JsonUtils.parseArray(queryParams, Filter.class));
        } catch (Exception e) {
            throw new EFWebException(FILTERS + " format error.");
        }
    }


    /**
     * 设置参数
     *
     * @param request http servlet request
     * @param page    分页对象
     */
    private void setParameter(HttpServletRequest request, Page<?> page) {
        String parameter = request.getParameter(PARAMETER);
        if (StringUtils.isBlank(parameter)) {
            return;
        }

        try {
            page.setParameter(JSON.parseObject(parameter, new TypeReference<Map<String, Object>>() {
            }));
        } catch (Exception e) {
            throw new EFWebException(PARAMETER + " format error.");
        }
    }


    /**
     * 将字符串转换为int，格式错误返回0
     *
     * @param name string
     * @return int
     */
    private int parseInt(String name) {
        if (StringUtils.isBlank(name) && name.matches(NUMBER_REGEX)) {
            return 0;
        }
        return Integer.parseInt(name);
    }

}
