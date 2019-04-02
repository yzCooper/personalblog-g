package com.yzblog.datacenter.web.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据验证
 */
public class FormatValid {

    /**
     * 验证是否为电话号码
     *
     */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    /**
     * 验证是否为数字
     *
     */
    public static boolean isMath(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1-9]+[0-9]*]*$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }


}
