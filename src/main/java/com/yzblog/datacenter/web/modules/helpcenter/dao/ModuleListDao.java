package com.yzblog.datacenter.web.modules.helpcenter.dao;


import com.yzblog.datacenter.web.modules.helpcenter.entity.ModuleList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ModuleListDao {
    int deleteByPrimaryKey(String mid);

    int insert(ModuleList record);

    int insertSelective(ModuleList record);

    ModuleList selectByPrimaryKey(String mid);

    int updateByPrimaryKeySelective(ModuleList record);

    int updateByPrimaryKey(ModuleList record);

    int selectNumberFromWid(@Param("wid") String wid);

    /**
     * 获取最新的五条数据
     * @return
     */
    List<ModuleList> latestContentList();
}