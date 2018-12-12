package com.example.util.test.annotation;

/**
 * @author yuanjie
 * @date 2018/11/28 10:28
 */
@Description("i am class annotation")
public class Child implements People {
    @Override
    @Description("i am method annotation")
    public String name() {
        return null;
    }

    @Override
    public int age() {
        return 0;
    }

    @Override
    public void work() {

    }
}
