package com.kevinyin.thread;

import java.util.ArrayList;
import java.util.List;

public class ThreadDemoCollectionTest {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        new DemoThread().start();
    }

    static class DemoThread extends Thread {

        public DemoThread() {
            super("demoThread5");
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                runTask();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


        public void runTask() {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 5000000; i++) {
                list.add(String.valueOf(i));
            }
        }
    }
}
