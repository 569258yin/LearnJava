package com.kevinyin.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

/**
 * 快速排序
 */
@Slf4j
public class QuickSortTest {


    public static void main(String[] args) {
        QuickSortTest quickSortTest = new QuickSortTest();
        int[] array = {1,31,2144,53,6546,3243,18,212,33245,65,50,88,29,19,230,1248,3910,213};
        quickSortTest.quickSort0(array,0,array.length-1);
        log.info("{}",array);
    }

    public void quickSort0(int[] a, int l, int r) {
        if (l < r) {
            int i = quickSort(a, l, r);
            quickSort0(a, l, i - 1);
            quickSort0(a, i + 1, r);
        }
    }

    public int quickSort(int[] a, int l, int r) {
        int i = l, j = r;
        int x = a[l];
        while (i < j) {
            while (i < j && a[j] >= x) {
                j--;
            }
            if (i < j) {
                a[i] = a[j];
                i++;
            }
            while (i < j && a[i] < x) {
                i++;
            }
            if (i < j) {
                a[j] = a[i];
                j--;
            }
        }
        a[i] = x;
        return i;
    }
}
