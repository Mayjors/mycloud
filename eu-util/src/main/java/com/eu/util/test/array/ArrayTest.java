package com.example.util.test.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuanjie
 * @date 2018/11/29 10:14
 */
public class ArrayTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("s");
        list.add("c");
        list.add("s");
        list.remove("s");
        System.out.println(list.size());



    }
}
