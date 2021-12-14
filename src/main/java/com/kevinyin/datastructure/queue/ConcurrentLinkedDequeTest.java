package com.kevinyin.datastructure.queue;

import org.apache.commons.lang3.RandomUtils;

import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author yinhao
 * @Description TODO
 * @Date 2021/12/1 13:27
 */
public class ConcurrentLinkedDequeTest {

    public static void main(String[] args) {
        ConcurrentLinkedDeque<Integer> concurrentLinkedDeque = new ConcurrentLinkedDeque<>();

        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                concurrentLinkedDeque.add(i);
                try {
                    Thread.sleep(RandomUtils.nextInt(100, 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                Integer value = concurrentLinkedDeque.pollFirst();
                if (Objects.nonNull(value)) {
                    System.out.println(value);
                } else {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
