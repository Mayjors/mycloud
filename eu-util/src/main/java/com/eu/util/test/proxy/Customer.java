package com.eu.util.test.proxy;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author yuanjie
 * @date 2019/1/8 14:20
 */
public class Customer {

    public static void main(String[] args) {
        dynamicClient();
        byte[] classFile = ProxyGenerator.generateProxyClass("TestProxyGen", JavaCoder.class.getInterfaces());
        File file = new File("F:/TestProxyGen.class");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(classFile);
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

    /**
     * 静态代理
     */
    private static void client() {
        // 定义一个码农
        ICoder coder = new JavaCoder("zhang");
        // 定义一个产品经理
        ICoder proxy = new CoderProxy(coder);
        // 让产品经理实现一个需求
        proxy.implDemands("Add user manageMent");
    }

    /**
     * 动态代理
     */
    private static void dynamicClient() {
        ICoder coder = new JavaCoder("Zhang");
        // 创建中介类实例
        InvocationHandler handler = new CoderDynamicProxy(coder);
        // 获取类加载器
        ClassLoader c1 = coder.getClass().getClassLoader();
        // 动态产生一个代理类
        ICoder proxy = (ICoder) Proxy.newProxyInstance(c1, coder.getClass().getInterfaces(), handler);
        proxy.implDemands("Modify user management");
    }
}
