package com.yzblog.datacenter.web.modules.helpcenter.service.impl;

import com.yzblog.datacenter.web.modules.helpcenter.dao.ApplicationListDao;
import com.yzblog.datacenter.web.modules.helpcenter.dao.HelpCenterSourceDao;
import com.yzblog.datacenter.web.modules.helpcenter.dao.ModuleListDao;
import com.yzblog.datacenter.web.modules.helpcenter.entity.ApplicationList;
import com.yzblog.datacenter.web.modules.helpcenter.entity.HelpCenterSource;
import com.yzblog.datacenter.web.modules.helpcenter.entity.ModuleList;
import com.yzblog.datacenter.web.modules.helpcenter.pojo.AppModulePo;
import com.yzblog.datacenter.web.modules.helpcenter.service.AppModuleSerivce;
import com.yzblog.datacenter.web.modules.helpcenter.util.FileUtills;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author yuzhou
 * @Description
 * @date created in 2018/12/24 10:20
 */
@Service
public class AppModuleSerivceImpl implements AppModuleSerivce {

    private static  final Logger log = LoggerFactory.getLogger(AppModuleSerivceImpl.class);

    @Autowired
    ApplicationListDao applicationListDao;

    @Autowired
    ModuleListDao moduleListDao;

    @Autowired
    HelpCenterSourceDao helpCenterSourceDao;

    @Override
    public Map<String,List<AppModulePo>> selectApplicationAndModule() {
        List<AppModulePo> list = applicationListDao.selectApplicationAndModule();
        Map<String,List<AppModulePo>> appModuleMap = new HashMap<>();
        if(list.size() > 0){
            for(AppModulePo appModulePo : list){
                List<AppModulePo> tempList = appModuleMap.get(appModulePo.getWid());
                if (tempList == null) {
                    tempList = new ArrayList<>();
                    tempList.add(appModulePo);
                    appModuleMap.put(appModulePo.getWid(), tempList);
                }else{
                    tempList.add(appModulePo);
                }
            }
        }
        return appModuleMap;
    }

    @Override
    public int insertSelective(ApplicationList record) {
        return applicationListDao.insertSelective(record);
    }

    @Override
    public int insertSelective(ModuleList record) {
        return moduleListDao.insertSelective(record);
    }

    @Override
    public int saveSource(String path,String sourcedata ,HelpCenterSource record) {
        boolean fileres = false;
        try {
             fileres = FileUtills.saveFile(path,sourcedata);
        }catch (Exception e){
            log.info("文件保存失败"+e.getMessage());
        }
        if(fileres){
            Date date = new Date();
            record.setUpdateTime(date);
            if(helpCenterSourceDao.updateByWidMid(record) <= 0){
                record.setCreateTime(date);
                int result = helpCenterSourceDao.insertSelective(record);
                if(result > 0){
                    return 1;
                }else{
                    return 0;
                }
            }else{
                return 1;
            }
        }else{
            return 0;
        }
    }

    @Override
    public int updateApplication(ApplicationList record) {
        return applicationListDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delApplication(String wid) {
        return applicationListDao.deleteByPrimaryKey(wid);
    }

    @Override
    public int updateModule(ModuleList record) {
        return moduleListDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delModule(String mid) {
        return moduleListDao.deleteByPrimaryKey(mid);
    }

    @Override
    public List<AppModulePo> selectFromWid(String wid) {
        return applicationListDao.selectFromWid(wid);
    }

}
