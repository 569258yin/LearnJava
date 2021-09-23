package com.kevinyin.thread;

public class ThreadDemoCpuTest {


    public static void main(String[] args) {
        DemoThread thread = new DemoThread();
        thread.start();
    }


    static class DemoThread extends Thread {

        public DemoThread() {
            super("demoThread1");
        }

        @Override
        public void run() {
            while (true) {
                runTask();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {

                }
            }
        }


        public void runTask() {
            double p = 2;
            for (int i = 0; i < 500000000; i++) {
                p *= ((double) (((int) ((i + 2) / 2)) * 2)) / (((int) ((i + 1) / 2)) * 2 + 1);
            }
            System.out.println("Thread run result=" + p);
        }
    }
}
