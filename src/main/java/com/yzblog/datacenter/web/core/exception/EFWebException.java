package com.yzblog.datacenter.web.core.exception;

/**
 * 系统运行时，主动抛出异常对象
 * Created by hua on 2016/12/29.
 */
public class EFWebException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EFWebException(Throwable cause) {
        super(cause);
    }

    public EFWebException(String message) {
        super(message);
    }
}
