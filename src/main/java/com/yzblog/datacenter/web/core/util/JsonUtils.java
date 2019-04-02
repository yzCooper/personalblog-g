package com.yzblog.datacenter.web.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * json工具类
 *
 * @author yuzhou
 * @create 2017-10-28 10:32
 **/
public class JsonUtils {

    /**
     * 对象转json字符串
     *
     * @param obj 对象
     * @return json字符串
     */
    public static String toJSONString(Object obj) {
        if (obj == null) {
            return null;
        }
        return JSON.toJSONString(obj);
    }

    /**
     * 对象转json字符串
     *
     * @param obj      对象
     * @param features 字段格式化
     * @return json字符串
     */
    public static <T> String toJSONString(Object obj, SerializerFeature... features) {
        if (obj == null) {
            return null;
        }
        return JSON.toJSONString(obj, features);
    }

    /**
     * json字符串转对象
     *
     * @param json json字符串
     * @param <T>  对象class
     * @return T
     */
    public static <T> T parseObject(String json, Class<T> t) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        return JSON.parseObject(json, t);
    }

    /**
     * json字符串转对象
     *
     * @param json json字符串
     * @param <T>  对象class
     * @return T
     */
    public static <T> T parseObject(String json, TypeReference<T> type) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        return JSON.parseObject(json, type);
    }


    /**
     * json字符串转数组
     *
     * @param json json字符串
     * @param <T>  对象class
     * @return T
     */
    public static <T> List<T> parseArray(String json, Class<T> t) {
        if (StringUtils.isBlank(json)) {
            return null;
        }

        return JSON.parseArray(json, t);
    }

}
