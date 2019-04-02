package com.yzblog.datacenter.web.core.dao;

import com.yzblog.datacenter.web.core.mybatis.query.Page;

import java.util.List;

/**
 * DAO支持类实现
 *
 * @author yuzhou
 */
public interface BaseDao<T> {

    public T get(String id);

    public T get(T entity);

    public List<T> listAll();

    public int insert(T entity);

    public int update(T entity);

    public int delete(T entity);

    /**
     * 查询所有，分页
     * @param page
     * @return
     */
    public List<T> selectByPage(Page<T> page);

    /**
     * 查询所有，非分页
     * @return
     */
    List<T> selectAll();

}