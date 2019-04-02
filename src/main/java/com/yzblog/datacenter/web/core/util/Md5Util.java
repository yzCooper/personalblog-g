package com.yzblog.datacenter.web.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 *
 * @author yuzhou
 */
public class Md5Util {

    private static final Logger LOG = LoggerFactory.getLogger(Md5Util.class);

    private static final String MD5_ALGORITHM = "MD5";

    /**
     * 字符串md5加密
     *
     * @param str 需要加密的字符串
     * @return 加密之后的字符串
     */
    public static String md5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance(MD5_ALGORITHM);
            md.update(str.getBytes());
            byte[] byteDigest = md.digest();

            StringBuilder buf = new StringBuilder(32);
            for (int digest : byteDigest) {
                if (digest < 0) {
                    digest += 256;
                }
                if (digest < 16) {
                    buf.append("0");
                }

                buf.append(Integer.toHexString(digest));
            }
            return buf.toString();

        } catch (NoSuchAlgorithmException e) {
            LOG.error("NoSuchAlgorithmException");
        }

        return null;
    }

}
