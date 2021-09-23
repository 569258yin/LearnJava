package com.kevinyin.thread.runtask;

import org.springframework.util.StopWatch;

public class PiCalTask implements Runnable {

    @Override
    public void run() {
        StopWatch stopWatch = new StopWatch();
        double p = 2;
        stopWatch.start();
        for (int i = 0; i < 500000000; i++) {
            p *= ((double) (((int) ((i + 2) / 2)) * 2)) / (((int) ((i + 1) / 2)) * 2 + 1);
        }
        stopWatch.stop();
        System.out.println("Thread run result=" + p + ",use time = " + stopWatch.getTotalTimeMillis() + "ms");
    }
}
