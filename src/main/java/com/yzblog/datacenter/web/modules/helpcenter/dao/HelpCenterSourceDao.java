package com.yzblog.datacenter.web.modules.helpcenter.dao;


import com.yzblog.datacenter.web.modules.helpcenter.entity.HelpCenterSource;
import org.apache.ibatis.annotations.Param;

public interface HelpCenterSourceDao {
    int deleteByPrimaryKey(String sid);

    int insert(HelpCenterSource record);

    int insertSelective(HelpCenterSource record);

    HelpCenterSource selectByPrimaryKey(String sid);

    int updateByPrimaryKeySelective(HelpCenterSource record);

    int updateByPrimaryKey(HelpCenterSource record);

    int updateByWidMid(HelpCenterSource record);

    String getSourcePath(@Param("wid") String wid, @Param("mid") String mid);
}