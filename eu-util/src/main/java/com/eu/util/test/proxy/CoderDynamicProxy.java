package com.eu.util.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理
 * @author yuanjie
 * @date 2019/1/8 14:25
 */
public class CoderDynamicProxy implements InvocationHandler {
    // 被代理的实例
    private ICoder coder;

    public CoderDynamicProxy(ICoder coder) {
        this.coder = coder;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(System.currentTimeMillis());
        Object invoke = method.invoke(coder, args);
        System.out.println(System.currentTimeMillis());
        return invoke;
    }
}
