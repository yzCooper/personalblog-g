package com.yzblog.datacenter.web.modules.helpcenter.dao;


import com.yzblog.datacenter.web.modules.helpcenter.entity.ApplicationList;
import com.yzblog.datacenter.web.modules.helpcenter.pojo.AppModulePo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ApplicationListDao {
    int deleteByPrimaryKey(String wid);

    int insert(ApplicationList record);

    int insertSelective(ApplicationList record);

    ApplicationList selectByPrimaryKey(String wid);

    int updateByPrimaryKeySelective(ApplicationList record);

    int updateByPrimaryKey(ApplicationList record);

    List<AppModulePo> selectApplicationAndModule();

    List<AppModulePo> selectFromWid(@Param("wid") String wid);

    /**
     * 获取所有标签
     * @return
     */
    List<Map<String,String>> selectWidAppName();

    /**
     * 获取对应标签下的博文标题
     * @param wid 标签id
     * @return
     */
    List<Map<String,String>> selectAllWidMidAndName(@Param("wid")String wid);
}