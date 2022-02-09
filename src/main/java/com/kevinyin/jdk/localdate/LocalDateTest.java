package com.kevinyin.jdk.localdate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author yinhao
 * @Description TODO
 * @Date 2022/1/21 16:01
 */
public class LocalDateTest {


    public static void main(String[] args) {
        testLocalDateThisTime();
    }

    public static void testLocalDateThisTime() {
        String text = "2021-11-12 00:30:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(text, formatter);
        System.out.println("hour = " + localDateTime.getHour());
        System.out.println("minute = " + localDateTime.getMinute());
    }
}
