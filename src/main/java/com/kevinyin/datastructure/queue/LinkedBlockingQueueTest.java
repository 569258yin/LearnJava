package com.kevinyin.datastructure.queue;

import org.apache.commons.lang3.RandomUtils;

import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author yinhao
 * @Description TODO
 * @Date 2021/12/2 13:48
 */
public class LinkedBlockingQueueTest {

    public static void main(String[] args) {
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue(10);

        new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                try {
                    queue.put(i);
                } catch (InterruptedException e) {
                }
                try {
                    Thread.sleep(RandomUtils.nextInt(100, 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                Integer value = null;
                try {
                    value = queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (Objects.nonNull(value)) {
                    System.out.println(value);
                }
            }
        }).start();
    }
}
