package com.kevinyin.lsocket;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Kevin_Yin on 2017/6/30.
 */
public class TcpIpServer {

    private static Logger logger = Logger.getLogger(TcpIpClient.class);
    private static ExecutorService poolService = Executors.newSingleThreadExecutor();

    private static final int TIMEOUT = 50000;

    public static void main(String[] args){
        ServerSocket server = null;
        try {
            server = new ServerSocket();
            server.bind(new InetSocketAddress("127.0.0.1", 58001));

            while (true) {
                Socket socket = null;
                ObjectInputStream ois = null;
                try {
                    socket = server.accept();
                    logger.info("Server got request @:" + System.currentTimeMillis());
                    socket.setSoTimeout(TIMEOUT);
                    ois = new ObjectInputStream(socket.getInputStream());
                    TcpIpMessage message = (TcpIpMessage) ois.readObject();
                    if(message != null){
                        poolService.submit(new ServerThread(message.getType(),message.getMessage(),socket,ois));
                    }
                    logger.debug("END........");
                } catch (Exception e) {
                    logger.error("Exception on Server",e);
                }finally {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
