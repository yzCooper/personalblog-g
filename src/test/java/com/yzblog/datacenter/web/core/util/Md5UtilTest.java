package com.yzblog.datacenter.web.core.util;

import com.yzblog.datacenter.web.modules.helpcenter.service.AppModuleSerivce;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * MD5Util单元测试
 *
 * @author yuzhou
 **/
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = DatacenterWebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Md5UtilTest {

    @Autowired
    AppModuleSerivce appModuleSerivce;

    @Test
    public void test() {
        System.out.println(Md5Util.md5("jsgyadmin"));
    }

    @Test
    public void test1() {
        System.out.println(StringDateUtils.dayCompareMinuteString("2018-08-12 19:16:12","2018-08-12 19:24:59","yyyy-MM-dd HH:mm:ss"));
        System.out.println(new Date(System.currentTimeMillis() + 2 * 60 * 1000));
    }

    @Test
    public void test2(){
//        Map<String,List<AppModulePo>> map = appModuleSerivce.selectApplicationAndModule();
//        System.out.println(map);
        String a = "1";
        System.out.println(StringUtils.isBlank(a));
    }

}
