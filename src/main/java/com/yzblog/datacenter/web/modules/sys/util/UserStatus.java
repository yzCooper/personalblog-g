package com.yzblog.datacenter.web.modules.sys.util;

/**
 * 用户状态
 *
 * @author yuzhou
 **/
public enum UserStatus {
    /**
     * {@code 0 enabled}.
     * 启用
     */
    ENABLED((short) 0),

    /**
     * {@code 1 disabled}.
     * 停用
     */
    DISABLED((short) 1),

    /**
     * {@code 2 deleted}.
     * 删除
     */
    DELETED((short) 2);

    private short value;

    public short getValue() {
        return value;
    }

    UserStatus(short value) {
        this.value = value;
    }

}
