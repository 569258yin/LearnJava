package com.kevinyin.thread;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ThreadDemoIoTest {

    public static final String URL = "http://localhost:8088/slowTime/sleepTime?time=5000";

    public static void main(String[] args) {
        DemoThread2 thread2 = new DemoThread2();
        thread2.start();
    }

    static class DemoThread2 extends Thread {

        public DemoThread2() {
            super("DemoThread2");
        }

        @Override
        public void run() {
            while (true) {
                runTask();
            }
        }

        public void runTask() {
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(URL)
                        .get()
                        .build();
                try (Response response = client.newCall(request).execute()) {
                    String resStr = response.body().string();
                    System.out.println("res=" + resStr);
                }
            } catch (Exception e) {
                System.err.println("调用服务失败:" + e.getMessage());
            }
        }
    }
}
