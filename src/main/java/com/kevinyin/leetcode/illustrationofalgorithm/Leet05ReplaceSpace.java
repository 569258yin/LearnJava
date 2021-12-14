package com.kevinyin.leetcode.illustrationofalgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 */
public class Leet05ReplaceSpace {

    public static void main(String[] args) {
        String s = "We are happy.";
        String s1 = leetCode(s);
        System.out.println(s1);
    }

    public static String mainCode(String s) {
        char[] charArray = s.toCharArray();
        List<Character> l = new ArrayList<Character>();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == ' ') {
                l.add('%');
                l.add('2');
                l.add('0');
            } else {
                l.add(charArray[i]);
            }
        }
        char[] newCharArray = new char[l.size()];
        for (int i = 0; i < l.size(); i++) {
            newCharArray[i] = l.get(i);
        }
        return new String(newCharArray);
    }

    public static String leetCode(String s) {
        int length = s.length();
        char[] charArray = new char[length * 3];
        int size = 0;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                charArray[size++] = '%';
                charArray[size++] = '2';
                charArray[size++] = '0';
            } else {
                charArray[size++] = c;
            }
        }
        return new String(charArray, 0, size);
    }
}
