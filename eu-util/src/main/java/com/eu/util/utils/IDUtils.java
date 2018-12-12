package com.example.util.utils;

import java.util.Random;

/**
 * 各种id生成策略
 * @author yuanjie
 * @date 2018/9/19 18:09
 */
public class IDUtils {

    /**
     * 图片名生成
     * @return
     */
    public static String genImageName() {
        // 取当前时间的长整型值包含毫秒
        long millis = System.currentTimeMillis();
        // 加上三位随机数
        Random random = new Random();
        int end3 = random.nextInt(999);
        // 如果不足三位钱补0
        String str = millis + String.format("%03d" + end3);
        return str;
    }
}
