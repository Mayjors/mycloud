package com.example.util.test.sort;

/**
 * 排序方法
 * @author yuanjie
 * @date 2018/9/25 14:15
 */
public class SortTest {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int[] a = {10, 100, 3, 51, 29, 72};
//        bubbleSort(a);
//        insertionSort(a);
        quickSort(a, 0, a.length - 1);
        for (int x : a) {
            System.out.print(x + " ");
        }
        long end = System.currentTimeMillis();
        System.out.println((end -start));
    }
    /**
     * 冒泡排序
     * @param numbers
     */
    public static void bubbleSort(int[] numbers) {
        int temp = 0;
        int size = numbers.length;
        for (int i = 0; i< size-1; i++) {
            for (int j = 0; j< size-1-i; j++) {
                if (numbers[j] > numbers[j+1]) {
                    temp = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = temp;
                }
            }
        }
    }

    /**
     * 插入排序
     * @param numbers
     */
    public static void insertionSort(int[] numbers) {
        int i, j, t = 0;
        for (i = 1; i < numbers.length; i++) {
            if (numbers[i] < numbers[i-1]) {
                t = numbers[i];
                for (j = i-1; j >=0 && t<numbers[j]; j--) {
                    numbers[j+1] = numbers[j];
                }
                numbers[j+1] = t;
            }
        }
    }

    /**
     * 选择排序法
     * @param numbers
     */
    public static void selectSort(int[] numbers) {
        int t = 0;
        for (int i = 0; i< numbers.length-1; i++) {
            int index = i;
            for (int j = i+1; j < numbers.length; j++) {
                if (numbers[index] > numbers[j]) {
                    index = j;
                }
            }
            if (index != i) {
                // 找到了比 numbers[i]小的则与numers[i]交换位置
                t = numbers[i];
                numbers[i] = numbers[index];
                numbers[index] = t;
            }
        }
    }

    /**
     * 快速排序
     * @param array
     * @param low = 0
     * @param high = array.length -1
     */
    public static void quickSort(int array[], int low, int high) {
        int pivot, p_pos, i, t; // pivot -> 位索引; p_pos ->轴值
        if (low < high) {
            p_pos = low;
            pivot = array[p_pos];
            for (i = low+1; i <=high; i++) {
                if (array[i] > pivot) {
                    p_pos ++;
                    t = array[p_pos];
                    array[p_pos] = array[i];
                    array[i] = t;
                }
                t = array[low];
                array[low] = array[p_pos];
                array[p_pos] = t;

                // 分而治之
                quickSort(array, low, p_pos - 1);   // 排序左半边
                quickSort(array, p_pos+1, high);     // 排序右半边
            }
        }
    }


}
