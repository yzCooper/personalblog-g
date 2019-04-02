package com.yzblog.datacenter.web.modules.helpcenter.service.impl;

import com.yzblog.datacenter.web.modules.helpcenter.dao.HelpCenterSourceDao;
import com.yzblog.datacenter.web.modules.helpcenter.entity.HelpCenterSource;
import com.yzblog.datacenter.web.modules.helpcenter.service.BlogSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuzhou
 * @Description
 * @date created in 2019/3/30 13:32
 */
@Service
public class BlogSourceServiceImpl implements BlogSourceService {
    @Autowired
    HelpCenterSourceDao helpCenterSourceDao;


    @Override
    public HelpCenterSource selectByPrimaryKey(String sid) {
        return helpCenterSourceDao.selectByPrimaryKey(sid);
    }
}
