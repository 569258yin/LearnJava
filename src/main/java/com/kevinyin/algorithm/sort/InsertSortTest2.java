package com.kevinyin.algorithm.sort;

/**
 * 插入排序
 *
 * @Description TODO
 * @Date 2021/12/14 15:01
 */
public class InsertSortTest2 {


    public static void main(String[] args) {
        int[] array = new int[]{1, 23, 51, 6765, 34, 52, 521, 23, 45, 2};
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i;
            while (j > 0) {
                if (array[j - 1] <= temp) {
                    break;
                }
                array[j] = array[j - 1];
                j--;
            }
            array[j] = temp;
        }
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ",");
        }
    }


}
