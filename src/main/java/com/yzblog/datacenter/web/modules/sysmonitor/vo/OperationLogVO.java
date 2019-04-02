package com.yzblog.datacenter.web.modules.sysmonitor.vo;


import com.yzblog.datacenter.web.modules.sysmonitor.entity.DsSOperationLog;

import java.io.Serializable;

public class OperationLogVO extends DsSOperationLog implements Serializable {

    private String userName;//应用中文名

    private String xm;//姓名

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }
}