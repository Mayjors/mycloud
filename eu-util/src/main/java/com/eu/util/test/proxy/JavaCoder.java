package com.eu.util.test.proxy;

/**
 * @author yuanjie
 * @date 2019/1/8 14:18
 */
public class JavaCoder implements ICoder {

    private String name;

    public JavaCoder(String name) {
        this.name = name;
    }

    @Override
    public void implDemands(String demandName) {
        System.out.println(name + " implemented demand: " + demandName + " in JAVA!");
    }
}
