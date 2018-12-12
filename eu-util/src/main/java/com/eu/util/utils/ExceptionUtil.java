package com.example.util.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 获取异常的堆栈信息
 * @author yuanjie
 * @date 2018/9/19 17:27
 */
public class ExceptionUtil {

    /**
     * 获取异常的堆栈信息
     * @param t
     * @return
     */
    public static String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        try {
            t.printStackTrace();
            return sw.toString();
        } finally {
            pw.close();
        }
    }
}
