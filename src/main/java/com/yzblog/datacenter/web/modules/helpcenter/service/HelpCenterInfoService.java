package com.yzblog.datacenter.web.modules.helpcenter.service;

import com.yzblog.datacenter.web.modules.helpcenter.entity.ModuleList;
import com.yzblog.datacenter.web.modules.helpcenter.vo.HelpSourceVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author yuzhou
 * @Description
 * @date created in 2018/12/24 14:53
 */
public interface HelpCenterInfoService {
    /**
     * 获取博文内容
     * @param record
     * @return
     */
    String helpCenterInfo(HelpSourceVo record);

    /**
     * 获取所有博文标题以及分类id,博文id
     * @return
     */
    List<Map<String,String>> selectWidAppNameAndNum();

    /**
     * 获取对应标签下的博文标题
     * @param wid 标签id
     * @return
     */
    List<Map<String,String>> selectAllWidMidAndName(String wid);

    /**
     * 获取最新的文章列表
     */
    List<ModuleList> latestContentList();
}
