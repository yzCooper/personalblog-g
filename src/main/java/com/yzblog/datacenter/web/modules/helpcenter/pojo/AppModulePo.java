package com.yzblog.datacenter.web.modules.helpcenter.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yuzhou
 * @Description
 * @date created in 2018/12/24 10:14
 */
public class AppModulePo implements Serializable {
    /**
     * 应用id
     */
    private String wid;
    /**
     * 应用名称
     */
    private String appName;
    /**
     * 应用模块id
     */
    private String mid;

    /**
     * 应用资源id
     */
    private String sid;

    /**
     * 应用模块名称
     */
    private String moduleName;

    /**
     * 模块帮助文档的更新时间
     */
    private Date updateTime;

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
