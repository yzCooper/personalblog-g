package com.yzblog.datacenter.web.modules.helpcenter.service;

import com.yzblog.datacenter.web.modules.helpcenter.entity.HelpCenterSource;

/**
 * @author yuzhou
 * @Description
 * @date created in 2019/3/30 13:32
 */
public interface BlogSourceService {
    HelpCenterSource selectByPrimaryKey(String sid);
}
