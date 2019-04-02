package com.yzblog.datacenter.web.modules.sysmonitor.service;


import com.yzblog.datacenter.web.modules.sysmonitor.vo.OperationLogVO;
import com.yzblog.datacenter.web.core.mybatis.query.Page;

public interface OperationLogService {

    Page<OperationLogVO> selectOperationLog(Page<OperationLogVO> page);

}
