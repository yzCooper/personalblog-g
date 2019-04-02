package com.yzblog.datacenter.web.modules.sysmonitor.service.impl;

import com.yzblog.datacenter.web.modules.sysmonitor.dao.DsSOperationLogDao;
import com.yzblog.datacenter.web.core.mybatis.query.Page;
import com.yzblog.datacenter.web.modules.sysmonitor.service.OperationLogService;
import com.yzblog.datacenter.web.modules.sysmonitor.vo.OperationLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 应用访问日志表接口实现
 *
 * @author yuzhou
 * @create 2017-10-24 14:09
 **/
@Service
public class OperationLogImpl implements OperationLogService {
    private final DsSOperationLogDao dsSOperationLogDao;

    @Autowired
    public OperationLogImpl(DsSOperationLogDao dsSOperationLogDao) {
        this.dsSOperationLogDao = dsSOperationLogDao;
    }


    @Override
    public Page<OperationLogVO> selectOperationLog(Page<OperationLogVO> page) {
        List<OperationLogVO> list=dsSOperationLogDao.selectOperationLog(page);
        page.setRows(list);
        return page;
    }

}
