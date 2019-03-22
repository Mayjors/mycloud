package com.eu.servicedrools2.model;

import lombok.Data;

/**
 * Created by lcc on 2018/10/9.
 */
@Data
public class PersonForExcel {
    private String name;
    private int age;
    private String desc;

    public PersonForExcel() {
        super();
    }

    public PersonForExcel(String name, int age) {
        this.name = name;
        this.age = age;
    }


    @Override
    public String toString() {
        return "[name=" + name + ",age=" + age + ",desc=" + desc + "]";
    }
}

