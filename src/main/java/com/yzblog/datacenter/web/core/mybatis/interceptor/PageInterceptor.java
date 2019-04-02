package com.yzblog.datacenter.web.core.mybatis.interceptor;

import com.yzblog.datacenter.web.core.mybatis.query.MatchType;
import com.yzblog.datacenter.web.core.mybatis.query.ValueType;
import com.yzblog.datacenter.web.core.mybatis.query.Filter;
import com.yzblog.datacenter.web.core.mybatis.query.Page;
import com.yzblog.datacenter.web.core.util.Reflections;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Properties;


/**
 * 数据库分页插件，只拦截查询语句.
 *
 * @author yuzhou
 * @create 2017-10-30 15:06:09
 */
@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class PageInterceptor extends BaseInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(PageInterceptor.class);

    /**
     * 分页帮助方言，属性名称
     */
    private static final String PAGEHELPER_HELPERDIALECT = "pagehelper.helperDialect";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        final MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];

        Object parameter = invocation.getArgs()[1];
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Object parameterObject = boundSql.getParameterObject();

        // 获取分页参数对象
        Page page = getQueryPage(parameterObject);

        // 如果设置了分页对象，则进行分页
        if (page != null && StringUtils.isNotBlank(boundSql.getSql())) {
            pageHandle(invocation, boundSql, mappedStatement, page);
        }

        return invocation.proceed();
    }

    /**
     * 分页处理
     *
     * @param invocation      Invocation
     * @param boundSql        BoundSql
     * @param mappedStatement MappedStatement
     * @param page            分页对象
     */
    private void pageHandle(Invocation invocation, BoundSql boundSql, MappedStatement mappedStatement, Page<?> page) throws SQLException {
        Object parameterObject = boundSql.getParameterObject();
        String pageQuerySql = boundSql.getSql();

        if (CollectionUtils.isNotEmpty(page.getFilters())) {
            // 获取已经添加了参数的SQL
            pageQuerySql = getAddedParamsSql(page.getFilters(), pageQuerySql);
        }

        // 得到总记录数
        int count = SQLHelper.getCount(pageQuerySql, mappedStatement, parameterObject, boundSql);
        page.setTotal((long) count);

        //获取排序sql
        String orderBySql = getOrderBySql(page);

        String pageSql = SQLHelper.generatePageSql(pageQuerySql + orderBySql, page, dialect);
        LOG.debug("pageSql: {}", pageSql);

        // 重新设置分页查询SQL
        invocation.getArgs()[2] = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);
        BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), pageSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
        // 解决MyBatis 分页foreach 参数失效 start
        if (Reflections.getFieldValue(boundSql, "metaParameters") != null) {
            MetaObject mo = (MetaObject) Reflections.getFieldValue(boundSql, "metaParameters");
            Reflections.setFieldValue(newBoundSql, "metaParameters", mo);
        }

        MappedStatement newMs = copyFromMappedStatement(mappedStatement, new BoundSqlSqlSource(newBoundSql));
        invocation.getArgs()[0] = newMs;
    }

    /**
     * 获取排序sql
     *
     * @param page 分页对象
     */
    private String getOrderBySql(Page page) {
        if (StringUtils.isBlank(page.getSort())) {
            return "";
        }

        // java类转SQL
        StringBuilder orderSqlBuilder = new StringBuilder()
                .append(" order by ")
                .append(formateJavaToSQLColumn(page.getSort()).toUpperCase())
                .append(" ")
                .append(page.getOrder());

        return orderSqlBuilder.toString();
    }

    /**
     * 获取已经添加了参数的SQL
     *
     * @param queryParams 过滤查询参数
     * @param originalSql 原始SQL
     * @return 添加参数后的SQL
     */
    private String getAddedParamsSql(List<Filter> queryParams, String originalSql) {
        StringBuilder pageQuerySql = new StringBuilder();

        // 包装sql
        pageQuerySql.append("select * from ( ");
        pageQuerySql.append(originalSql);
        pageQuerySql.append(") t1");

        // 加上where
        pageQuerySql.append(" where 1=1 ");

        // 添加字段过滤条件
        String filterSql = getFilterSql(queryParams);
        pageQuerySql.append(filterSql);

        return pageQuerySql.toString();
    }

    /**
     * 添加参数
     *
     * @param queryParams 查询参数
     */
    private String getFilterSql(List<Filter> queryParams) {
        StringBuilder filterSql = new StringBuilder();

        for (Filter filter : queryParams) {
            filterSql.append("and t1.").append(formateJavaToSQLColumn(filter.getName()));

            MatchType matchType = filter.getMatchType();
            if (matchType == null) {
                // 默认like
                matchType = MatchType.LIKE;
            }

            ValueType valueType = filter.getValueType();
            if (valueType == null) {
                // 默认字符串类型
                valueType = ValueType.STRING;
            }

            String matchValueStr;
            switch (valueType) {
                case EXPRESSION:
                case NUMBER:
                    matchValueStr = filter.getValues();
                    break;
                case STRING:
                    matchValueStr = "'" + filter.getValues() + "'";
                    break;
                default:
                    matchValueStr = filter.getValues();
                    break;
            }

            if (matchType == MatchType.LIKE) {
                // 匹配类型为like则不加上''
                filterSql.append(" like '%").append(filter.getValues()).append("%'");
            } else {
                filterSql.append(" ").append(matchType.getValue()).append(matchValueStr);
            }
        }

        return filterSql.toString();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        // 获取方言
        String pageHelperDialect = properties.getProperty(PAGEHELPER_HELPERDIALECT);
        initPageHelperDialect(pageHelperDialect);
    }


    private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null) {
            for (String keyProperty : ms.getKeyProperties()) {
                builder.keyProperty(keyProperty);
            }
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.cache(ms.getCache());
        return builder.build();
    }

    /**
     * 将java 字段名称转换成SQL模式，如：columnName -> column_name
     *
     * @param columnName 字段名称
     * @return sql格式字段名称
     */
    private String formateJavaToSQLColumn(String columnName) {
        int indexOf = columnName.indexOf(".");
        if (indexOf > -1) {
            columnName = columnName.substring(indexOf);
        }

        StringBuilder sbf = new StringBuilder(columnName.length());
        boolean flag = true;
        for (int i = 0; i < columnName.length(); i++) {
            char tempChr = columnName.charAt(i);
            if (tempChr >= 'A' && tempChr <= 'Z' && flag) {
                // 如果是大写字母，则在字符前面插入一个_
                sbf.append("_");
                flag = false;
            } else {
                flag = true;
            }

            sbf.append(tempChr);
        }
        return sbf.toString();
    }

    public static class BoundSqlSqlSource implements SqlSource {
        BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        @Override
        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }
}
