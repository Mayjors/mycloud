package com.example.util.test.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author yuanjie
 * @date 2018/11/28 10:31
 */
public class ParseAnn {
    public static void main(String[] args) {
        try {
            // 使用类加载器加载类
            Class c = Class.forName("com.example.util.test.annotation.Child");
            // 找到类上面的注解
            boolean isExist = c.isAnnotationPresent(Description.class);
            if (isExist) {
                // 拿到注解实例，解析类上面的注解
                Description d = (Description) c.getAnnotation(Description.class);
                System.out.println(d.value());
            }

            Method[] ms = c.getMethods();
            for (Method m : ms) {
                boolean isExist1 = m.isAnnotationPresent(Description.class);
                if (isExist1) {
                    Description d1 = m.getAnnotation(Description.class);
                    System.out.println(d1.value());
                }
            }

            for (Method m :ms) {
                // 拿到方法上的所有注解
                Annotation[] as = m.getAnnotations();
                for (Annotation a : as) {
                    // 用二元操作符判断是否是Description实例
                    if (a instanceof Description) {
                        Description d = (Description) a;
                        System.out.println(d.value());
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
