package com.kevinyin.thread;

public class ThreadDemoLockTest {

    private static final Object LOCK = new Object();


    public static void main(String[] args) {
        DemoThread thread3 = new DemoThread("DemoThread3");
        DemoThread thread4 = new DemoThread("DemoThread4");
        thread3.start();
        thread4.start();
    }


    static class DemoThread extends Thread {
        public DemoThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (true) {
                runTask();
                System.out.println(Thread.currentThread().getName()+"运行完成");
            }
        }

        public void runTask() {
            synchronized (LOCK) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
