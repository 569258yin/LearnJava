package com.kevinyin.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class SelectSortTest {

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>(Arrays.asList(5, 3, 6, 2, 10));
        System.out.println(sort(arr)); //[2, 3, 5, 6, 10]
    }

    public static List<Integer> sort(List<Integer> arr) {
        if (arr == null || arr.size() <= 1) {
            return arr;
        }
        List<Integer> newArray = new ArrayList<>(arr.size());
        int size = arr.size();
        for (int i = 0; i < size; i++) {
            int small_index = findSmallestIndex(arr);
            newArray.add(arr.get(small_index));
            arr.remove(small_index);
        }
        return newArray;
    }

    public static int findSmallestIndex(List<Integer> array) {
        int smallest_index = 0;
        int smallest = array.get(smallest_index);
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) < smallest) {
                smallest = array.get(i);
                smallest_index = i;
            }
        }
        return smallest_index;
    }

}
