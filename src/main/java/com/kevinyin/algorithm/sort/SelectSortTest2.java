package com.kevinyin.algorithm.sort;

/**
 * 选择排序，每次选择最小的放到前面
 */
public class SelectSortTest2 {

    public static void main(String[] args) {
        int[] array = new int[]{1, 23, 51, 6765, 34, 52, 521, 23, 45, 2};
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;

        }
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ",");
        }
    }


}
