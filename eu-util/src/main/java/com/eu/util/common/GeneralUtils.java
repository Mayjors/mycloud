package com.example.util.common;

/**
 * 通用的工具类
 * @author yuanjie
 * @date 2018/9/18 9:37
 */
public class GeneralUtils {

    private GeneralUtils() {

    }

    /**
     * 判断对象是否为null，为null返回true、否则返回false
     * @param obj
     * @return
     */
    public static boolean isNull(Object obj) {
        return (null == obj) ? true : false;
    }

    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }






}
