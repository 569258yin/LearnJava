package com.kevinyin.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

/**
 * 快速排序
 */
@Slf4j
public class QuickSortTest2 {


    public static void main(String[] args) {
        QuickSortTest2 quickSortTest = new QuickSortTest2();
        int[] array = {72, 35, 97,82,63,54,77};
        quickSortTest.quickSort(array, 0, array.length - 1);
        log.info("{}", array);
    }

    public void quickSort(int arr[], int left, int right) {
        if (left < right) {
            /*将arr[left..right]均分为两部分arr[left..mid]和arr[mid+1..right]
             * ,以pivot为中轴，小放左，大放右。这是第一步。*/
            int mid = getMid(arr, left, right);
            quickSort(arr, left, mid - 1);
            quickSort(arr, mid + 1, right);
        }
    }

    public int getMid(int arr[], int left, int right) {
        int pivot = arr[left];
        while (left < right) {
            while (arr[right] >= pivot && left < right) {
                right--;
            }
            arr[left] = arr[right];
            while (arr[left] <= pivot && left < right) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = pivot;
        return left;
    }
}
