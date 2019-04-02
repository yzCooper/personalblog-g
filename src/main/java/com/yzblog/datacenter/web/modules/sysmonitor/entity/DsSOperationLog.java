package com.yzblog.datacenter.web.modules.sysmonitor.entity;

import java.io.Serializable;
import java.util.Date;

public class DsSOperationLog implements Serializable {
    private Long logId;

    private String logType;

    private String className;

    private String methodName;

    private Date reqTime;

    private String reqUrl;

    private String reqIp;

    private String userId;

    private String reqStatus;

    private String excpMessage;

    private String reqParam;

    private static final long serialVersionUID = 1L;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType == null ? null : logType.trim();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

    public Date getReqTime() {
        return reqTime;
    }

    public void setReqTime(Date reqTime) {
        this.reqTime = reqTime;
    }

    public String getReqUrl() {
        return reqUrl;
    }

    public void setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl == null ? null : reqUrl.trim();
    }

    public String getReqIp() {
        return reqIp;
    }

    public void setReqIp(String reqIp) {
        this.reqIp = reqIp == null ? null : reqIp.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(String reqStatus) {
        this.reqStatus = reqStatus == null ? null : reqStatus.trim();
    }

    public String getExcpMessage() {
        return excpMessage;
    }

    public void setExcpMessage(String excpMessage) {
        this.excpMessage = excpMessage == null ? null : excpMessage.trim();
    }

    public String getReqParam() {
        return reqParam;
    }

    public void setReqParam(String reqParam) {
        this.reqParam = reqParam;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", logId=").append(logId);
        sb.append(", logType=").append(logType);
        sb.append(", className=").append(className);
        sb.append(", methodName=").append(methodName);
        sb.append(", reqTime=").append(reqTime);
        sb.append(", reqUrl=").append(reqUrl);
        sb.append(", reqIp=").append(reqIp);
        sb.append(", userId=").append(userId);
        sb.append(", reqStatus=").append(reqStatus);
        sb.append(", excpMessage=").append(excpMessage);
        sb.append(", reqParam=").append(reqParam);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}