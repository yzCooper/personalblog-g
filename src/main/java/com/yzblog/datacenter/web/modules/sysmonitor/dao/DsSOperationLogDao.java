package com.yzblog.datacenter.web.modules.sysmonitor.dao;

import com.yzblog.datacenter.web.core.mybatis.query.Page;
import com.yzblog.datacenter.web.modules.sysmonitor.entity.DsSOperationLog;
import com.yzblog.datacenter.web.modules.sysmonitor.vo.OperationLogVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DsSOperationLogDao {
    int deleteByPrimaryKey(Long logId);

    int insert(DsSOperationLog record);

    int insertSelective(DsSOperationLog record);

    int updateByPrimaryKey(DsSOperationLog record);

    List<OperationLogVO> selectOperationLog(@Param("page") Page<OperationLogVO> page);

    List<Map<String,Object>> selCountByApplicationUrl(@Param("saUrl") String saUrl);
}