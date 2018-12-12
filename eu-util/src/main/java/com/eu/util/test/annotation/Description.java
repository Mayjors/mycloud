package com.example.util.test.annotation;

import java.lang.annotation.*;

/**
 * @author yuanjie
 * @date 2018/11/28 10:26
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Description {
    String value();
}
