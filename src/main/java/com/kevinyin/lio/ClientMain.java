package com.kevinyin.lio;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * Created by Kevin_Yin on 2017/7/4.
 */
@Slf4j
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
                            log.error("运行失败",e);
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
