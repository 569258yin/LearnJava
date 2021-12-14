package com.kevinyin.algorithm.sort;

/**
 * 插入排序
 *
 * @Description TODO
 * @Date 2021/12/14 15:01
 */
public class InsertSortTest {


    public static void main(String[] args) {
        int[] array = new int[]{1, 23, 51, 6765, 34, 52, 521, 23, 45, 2};
        for (int i = 1; i < array.length; i++) {
            /**
             * 这样实现好像不是插入排序了，
             */
            for (int j = i; j > 0; j--) {
                int temp = array[j];
                if (temp >= array[j - 1]) {
                    break;
                }
                array[j] = array[j - 1];
                array[j - 1] = temp;
            }
        }
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ",");
        }
    }


}
