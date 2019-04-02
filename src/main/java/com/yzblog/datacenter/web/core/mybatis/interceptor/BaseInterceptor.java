package com.yzblog.datacenter.web.core.mybatis.interceptor;


import com.yzblog.datacenter.web.core.mybatis.dialect.Dialect;
import com.yzblog.datacenter.web.core.mybatis.dialect.db.*;
import com.yzblog.datacenter.web.core.mybatis.query.Page;
import org.apache.ibatis.plugin.Interceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Mybatis分页拦截器基类
 *
 * @author yuzhou
 * @create 2017-10-30 14:27
 */
public abstract class BaseInterceptor implements Interceptor {
    private static final Logger LOG = LoggerFactory.getLogger(BaseInterceptor.class);

    /**
     * page参数注解名称
     */
    public static final String PAGE = "page";

    /**
     * 数据库方言
     */
    protected Dialect dialect;


    /**
     * 初始化数据库方言
     *
     * @param pageHelperDialect 数据库方言 参考{@link PageHelperDialect}
     */
    protected void initPageHelperDialect(String pageHelperDialect) {
        PageHelperDialect dialect = PageHelperDialect.valueOf(pageHelperDialect.toUpperCase());
        switch (dialect) {
            case DB2:
                this.dialect = new DB2Dialect();
                break;
            case DERBY:
                this.dialect = new DerbyDialect();
                break;
            case H2:
                this.dialect = new H2Dialect();
                break;
            case HSQL:
                this.dialect = new HSQLDialect();
                break;
            case MYSQL:
                this.dialect = new MySQLDialect();
                break;
            case ORACLE:
                this.dialect = new OracleDialect();
                break;
            case POSTGRE:
                this.dialect = new PostgreSQLDialect();
                break;
            case MSSQL:
            case SQLSERVER:
                this.dialect = new SQLServer2005Dialect();
                break;
            case SYBASE:
                this.dialect = new SybaseDialect();
                break;
            default:
                throw new NullPointerException("No matching dialect objects.");
        }
    }

    /**
     * 获取查询page对象
     *
     * @param parameter 参数对象
     * @return 分页对象 {@link Page}
     */
    protected Page<?> getQueryPage(Object parameter) {
        if (parameter == null) {
            return null;
        }

        try {
            if (parameter instanceof Page) {
                return (Page) parameter;

            } else if (parameter instanceof Map) {
                Map mybatisParameter = (Map) parameter;
                if (mybatisParameter.containsKey(PAGE)) {
                    return (Page) mybatisParameter.get(PAGE);
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }

        return null;
    }

}
