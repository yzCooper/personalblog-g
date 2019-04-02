package com.yzblog.datacenter.web.modules.helpcenter.entity;

import java.io.Serializable;
import java.util.Date;

public class ApplicationList implements Serializable {
    private String wid;

    private String appName;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid == null ? null : wid.trim();
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}