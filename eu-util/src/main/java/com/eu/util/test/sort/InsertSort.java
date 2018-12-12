package com.example.util.test.sort;

/**
 * 直接排序
 * @author yuanjie
 * @date 2018/9/25 14:05
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] a = {10,100,3,51, 29, 72};
        int length = a.length;
        int insertNum;
        for (int i=1; i< length; i++) {
            insertNum = a[i];
            int j = i-1;
            while (j >0 && a[j] > insertNum) {
                a[j+1] = a[j];
                j --;
            }
            a[j+1] = insertNum;
        }
        for (int x: a) {
            System.out.println(x);
        }
    }
}
