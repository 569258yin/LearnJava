package com.kevinyin.lio;

/**
 * Created by Kevin_Yin on 2017/7/4.
 */
public class ServerMain {

    public static void main(String[] args) {
        for (int i =52000; i < 52500; i++){
            final TcpIpServer tcpIpServer = new TcpIpServer(i);
            new Thread(new Runnable() {
                public void run() {
                    tcpIpServer.run();
                }
            }).start();
        }

    }
}
