package com.yzblog.datacenter.web.modules.helpcenter.service.impl;

import com.yzblog.datacenter.web.modules.helpcenter.dao.ApplicationListDao;
import com.yzblog.datacenter.web.modules.helpcenter.dao.HelpCenterSourceDao;
import com.yzblog.datacenter.web.modules.helpcenter.dao.ModuleListDao;
import com.yzblog.datacenter.web.modules.helpcenter.entity.ModuleList;
import com.yzblog.datacenter.web.modules.helpcenter.service.HelpCenterInfoService;
import com.yzblog.datacenter.web.modules.helpcenter.util.FileUtills;
import com.yzblog.datacenter.web.modules.helpcenter.vo.HelpSourceVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yuzhou
 * @Description
 * @date created in 2018/12/24 14:53
 */
@Service
public class HelpCenterInfoServiceImpl implements HelpCenterInfoService {

    private static  final Logger log = LoggerFactory.getLogger(HelpCenterInfoServiceImpl.class);

    @Value("${help.center.source.path}")
    private String path;

    @Autowired
    HelpCenterSourceDao helpCenterSourceDao;

    @Autowired
    ApplicationListDao applicationListDao;

    @Autowired
    ModuleListDao moduleListDao;

    @Override
    public String helpCenterInfo(HelpSourceVo record){
        String a = helpCenterSourceDao.getSourcePath(record.getWid(),record.getMid());
        StringBuilder sb = new StringBuilder(path).append(a);
        String source = FileUtills.readingFile(sb.toString());
        return source;
    }

    @Override
    public List<Map<String, String>> selectWidAppNameAndNum() {
        List<Map<String, String>> list = applicationListDao.selectWidAppName();
        List<Map<String,String>> reslist = new ArrayList<>();
        list.forEach(item -> {
            int num = moduleListDao.selectNumberFromWid(item.get("wid"));
            item.put("num",String.valueOf(num));
            reslist.add(item);
        });
        return reslist;
    }

    @Override
    public List<Map<String, String>> selectAllWidMidAndName(String wid) {
        return applicationListDao.selectAllWidMidAndName(wid);
    }

    @Override
    public List<ModuleList> latestContentList() {
        return moduleListDao.latestContentList();
    }
}
