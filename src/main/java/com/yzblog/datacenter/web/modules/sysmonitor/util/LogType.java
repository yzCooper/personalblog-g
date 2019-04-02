package com.yzblog.datacenter.web.modules.sysmonitor.util;

/**
 * 日志类型
 *
 * @author yuzhou
 * @create 2017-10-19 17:52
 **/
public enum LogType {
    /**
     * {@code 0 controller}.
     * 控制器层
     */
    CONTROLLER((short) 0),

    /**
     * {@code 1 service}.
     * service层
     */
    SERVICE((short) 1);

    private short value;

    LogType(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }
}
