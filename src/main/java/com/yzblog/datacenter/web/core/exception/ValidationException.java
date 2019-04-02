package com.yzblog.datacenter.web.core.exception;

/**
 * 参数以及数据格式校验异常
 * Created by hua on 2016/12/29.
 */
public class ValidationException extends EFWebException {

    public ValidationException(Throwable cause) {
        super(cause);
    }

    public ValidationException(String message) {
        super(message);
    }
}
