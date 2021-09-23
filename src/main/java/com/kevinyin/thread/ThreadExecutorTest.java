package com.kevinyin.thread;

import org.springframework.util.StopWatch;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadExecutorTest {


    public static void main(String[] args) throws InterruptedException {
        int coreSize = Runtime.getRuntime().availableProcessors();
        Executor executor = new ThreadPoolExecutor(coreSize, coreSize, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
        int taskCount = 20;
        CountDownLatch countDownLatch = new CountDownLatch(taskCount);
        StopWatch totalTimeWatch = new StopWatch();
        totalTimeWatch.start();
        for (int k = 0; k < taskCount; k++) {
            executor.execute(() -> {
                try {
                    StopWatch stopWatch = new StopWatch();
                    double p = 2;
                    stopWatch.start();
                    for (int i = 0; i < 500000000; i++) {
                        p *= ((double) (((int) ((i + 2) / 2)) * 2)) / (((int) ((i + 1) / 2)) * 2 + 1);
                    }
                    stopWatch.stop();
                    System.out.println("Thread run result=" + p + ",use time = " + stopWatch.getTotalTimeMillis() + "ms");
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        totalTimeWatch.stop();
        System.out.println("总任务耗费时间:" + totalTimeWatch.getTotalTimeMillis() + "ms");
    }
}
