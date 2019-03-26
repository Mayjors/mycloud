package com.eu.eurekaserver.utils;

/**
 * @author yuanjie
 * @date 2019/3/26 18:13
 */
public class MD5Service {
    public String getMD5(String input) {
        return MD5Util.getMD5(input.getBytes());
    }
}
