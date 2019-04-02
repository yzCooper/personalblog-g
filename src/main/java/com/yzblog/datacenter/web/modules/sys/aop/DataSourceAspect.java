package com.yzblog.datacenter.web.modules.sys.aop;

import com.yzblog.datacenter.web.db.DataSourceHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 *  Created by nbfujx on 2017/10/18.
 */
@Aspect
@Order(-99) // 控制多个Aspect的执行顺序，越小越先执行
@Component
public class DataSourceAspect {

    /**
     * 在service层获取datasource对象之前在切面中获取数据源
     */
    @Before("execution(* com.yzblog.datacenter.web.modules.*.service..*.*(..))")
    public void before(JoinPoint point)
    {
        Object target = point.getTarget();
        try {
            if(target!=null&&target.getClass().isAnnotationPresent(DataSource.class))
            {
                DataSource dataSource =target.getClass().getAnnotation(DataSource.class);
                DataSourceHolder.putDataSource(dataSource.value());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //执行完切面后，将线程共享中的数据源名称清空
    @After("execution(* com.yzblog.datacenter.web.modules.*.service..*.*(..))")
    public void after(JoinPoint joinPoint){
        DataSourceHolder.clearDataSource();
    }
}