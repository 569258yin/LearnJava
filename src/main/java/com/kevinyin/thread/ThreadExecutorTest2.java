package com.kevinyin.thread;

import org.springframework.util.StopWatch;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadExecutorTest2 {


    public static void main(String[] args) throws InterruptedException {
        Executor executor = new ThreadPoolExecutor(8, 50, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000));
//        Executor executor = new ThreadPoolExecutor(50, 50, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000));
//        Executor executor = new ThreadPoolExecutor(10, 50, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
        int taskCount = 1000;
        Set<Integer> tasks = Collections.synchronizedSet(new HashSet<>(taskCount));
        for (int i = 0; i < taskCount; i++) {
            tasks.add(i);
        }
        CountDownLatch countDownLatch = new CountDownLatch(taskCount);
        StopWatch totalTimeWatch = new StopWatch();
        totalTimeWatch.start();
        for (int k = 0; k < taskCount; k++) {
            final int l = k;
            try {
                executor.execute(() -> {
                    try {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                        }
                        System.out.println("执行任务" + l + "完成");
                        tasks.remove(l);
                    } finally {
                        countDownLatch.countDown();
                    }
                });
            } catch (Exception e) {
                System.err.println("任务提交失败" + e.getMessage());
            }

        }
        boolean v = countDownLatch.await(2000, TimeUnit.MILLISECONDS);
        if (!v) {
            System.out.println("result:" + tasks);
            throw new RuntimeException("任务未全部执行成功");
        }
        totalTimeWatch.stop();
        System.out.println("总任务耗费时间:" + totalTimeWatch.getTotalTimeMillis() + "ms");
        System.exit(0);
    }
}
