package com.yzblog.datacenter.web.modules.helpcenter.service;

import com.yzblog.datacenter.web.modules.helpcenter.entity.ApplicationList;
import com.yzblog.datacenter.web.modules.helpcenter.entity.HelpCenterSource;
import com.yzblog.datacenter.web.modules.helpcenter.entity.ModuleList;
import com.yzblog.datacenter.web.modules.helpcenter.pojo.AppModulePo;

import java.util.List;
import java.util.Map;

/**
 * @author yuzhou
 * @Description
 * @date created in 2018/12/24 10:19
 */
public interface AppModuleSerivce {
    Map<String,List<AppModulePo>> selectApplicationAndModule();

    /**
     * 新增标签
     * @param record
     * @return
     */
    int insertSelective(ApplicationList record);
    /**
     * 新增标题
     * @param record
     * @return
     */
    int insertSelective(ModuleList record);

    /**
     * 博文保存
     * @param path
     * @param sourcedata
     * @param record
     * @return
     */
    int saveSource(String path, String sourcedata,HelpCenterSource record);

    /**
     * 更新标签
     * @param record
     * @return
     */
    int updateApplication(ApplicationList record);

    /**
     * 删除标签
     * @param wid
     * @return
     */
    int delApplication(String wid);

    /**
     * 更新博文标题
     * @param record
     * @return
     */
    int updateModule(ModuleList record);

    /**
     * 删除标题
     * @param mid
     * @return
     */
    int delModule(String mid);

    /**
     * 根据wid获取资源
     * @param wid
     * @return
     */
    List<AppModulePo> selectFromWid(String wid);

}
