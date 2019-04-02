/*
* Copyright (c) 2017-2018 the hiteqinfo company.
*/
package com.yzblog.datacenter.web.core.util;

import java.util.UUID;

/**
 * UUID工具类，支持生成32位uuid
 *
 * @author yuzhou
 * @create 2017-10-20 10:23
 **/
public class UUIDUtils {

    /**
     * 根据jdk生成默认uuid
     *
     * @return uuid字符串
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 根据jdk生成32位uuid
     *
     * @return uuid字符串
     */
    public static String generateUUID32() {
        UUID uuid = UUID.randomUUID();
        long mostSigBits = uuid.getMostSignificantBits();
        long leastSigBits = uuid.getLeastSignificantBits();

        return (digits(mostSigBits >> 32, 8) +
                digits(mostSigBits >> 16, 4) +
                digits(mostSigBits, 4) +
                digits(leastSigBits >> 48, 4) +
                digits(leastSigBits, 12));
    }

    /**
     * Returns val represented by the specified number of hex digits.
     */
    private static String digits(long val, int digits) {
        long hi = 1L << (digits * 4);
        return Long.toHexString(hi | (val & (hi - 1))).substring(1);
    }

}
