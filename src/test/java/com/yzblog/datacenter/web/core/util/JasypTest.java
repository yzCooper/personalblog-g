package com.yzblog.datacenter.web.core.util;

import org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI;
import org.junit.Test;

/**
 * @author yuzhou
 * @Description
 * @date created in 2018/12/26 14:08
 */
public class JasypTest {

    @Test
    public void test() {
        // input：数据库密码
        //  password：加密密码
        JasyptPBEStringEncryptionCLI.main(new String[]{"input=yz_blog_mysql", "password=helpcenter", "algorithm=PBEWithMD5AndDES"});

        //最下面的OUTPUT，为加密后的数据库密码，通过ENC(xxx)包起来放到properties文件中，spring会自动解密
    }
}
