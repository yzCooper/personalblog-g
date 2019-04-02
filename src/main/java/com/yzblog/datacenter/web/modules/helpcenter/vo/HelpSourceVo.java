package com.yzblog.datacenter.web.modules.helpcenter.vo;

import com.yzblog.datacenter.web.modules.helpcenter.entity.HelpCenterSource;

/**
 * @author yuzhou
 * @Description
 * @date created in 2018/12/22 16:09
 */
public class HelpSourceVo extends HelpCenterSource {

    private String sourcedata;

    private String appName;

    private String moduleName;

    public String getSourcedata() {
        return sourcedata;
    }

    public void setSourcedata(String sourcedata) {
        this.sourcedata = sourcedata;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
}
