package com.kevinyin.lock.redis;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class RedisLockTest {

    private static int TOTAL = 100;

    public static void main(String[] args) throws InterruptedException {

        RedisLock lock = new RedisLock();
        CountDownLatch countDownLatch = new CountDownLatch(5);
        try {
            for (int j = 0; j < 10; j++) {
                new Thread(() -> {
                    String threadName = Thread.currentThread().getName();
                    while (TOTAL > 0) {
                        if (lock.lock(threadName, 2000)) {
                            if (TOTAL > 0) {
                                TOTAL--;
                                log.info("{}目前总数为:{}", threadName, TOTAL);
                            }
                            int i = (int) (1 + 10 * Math.random());
                            if (i % 3 == 0) {
                                log.warn("程序出错，未正常释放锁");
                                continue;
                            }
                            lock.unLock(threadName);
                        } else {
                            log.warn("{}当前无法获取到锁", threadName);
                        }
                    }
                    countDownLatch.countDown();
                }).start();
            }
        } finally {
            try {
                countDownLatch.await();
            } catch (Exception e) {
                log.error("等待任务终止出现异常", e);
            }
            lock.shutdown();
        }
    }
}
