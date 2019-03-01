package com.eu.util.test.simple;

/**
 * @author yuanjie
 * @date 2018/12/28 11:28
 */
public class SonTest extends FatherTest {
    private String name;
    static {
        System.out.println("--子类的静态代码块--");
    }
    {
        System.out.println("--子类的非静态代码块--");
    }

    public SonTest() {
        System.out.println("--子类的无参构造函数--");
    }

    public SonTest(String name) {
        this.name = name;
        System.out.println("--子类的有参构造函数--");
    }

    @Override
    public void speak() {
        super.speak();
        System.out.println("--子类Override了父类的方法--");
    }

    public static void main(String[] args) {
        System.out.println("--子类主程序--");
        FatherTest fater = new FatherTest();
        fater.speak();
        SonTest son = new SonTest();
        son.speak();
    }
}
