package com.kevinyin.lio;

import java.util.Random;

/**
 * Created by Kevin_Yin on 2017/7/4.
 */
public class ClientMain {
    static Random random = new Random();
    public static void main(String[] args) {

        for (int i = 52000; i <52500; i++){
            final TcpIpClient tcpIpClient = new TcpIpClient(i);
            new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            tcpIpClient.run();
                        }catch (Exception e){
                            DebugLog.logger.error(e);
                        }
                        try {
                            Thread.sleep(random.nextInt(5000));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }
}
