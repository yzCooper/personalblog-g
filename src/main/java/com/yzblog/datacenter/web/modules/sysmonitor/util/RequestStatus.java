package com.yzblog.datacenter.web.modules.sysmonitor.util;

/**
 * 请求状态
 *
 * @author yuzhou
 * @create 2017-10-19 17:52
 **/
public enum RequestStatus {
    /**
     * {@code 0 success}.
     * 成功
     */
    SUCCESS((short) 0),

    /**
     * {@code 1 fail}.
     * 异常失败
     */
    FAIL((short) 1);

    private short value;

    RequestStatus(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }
}
