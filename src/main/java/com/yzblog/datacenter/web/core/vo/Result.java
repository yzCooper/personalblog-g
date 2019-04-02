package com.yzblog.datacenter.web.core.vo;

import java.io.Serializable;

/**
 * @description：操作结果集
 */
public class Result implements Serializable {

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 返回内容
     */
    private Object data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
