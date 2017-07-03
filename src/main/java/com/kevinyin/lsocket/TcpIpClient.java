package com.kevinyin.lsocket;

import org.apache.log4j.Logger;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by Kevin_Yin on 2017/6/30.
 */
public class TcpIpClient {
    private static Logger logger = Logger.getLogger(TcpIpClient.class);
    public static void main(String[] args) {
        Socket socket = null;
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        TcpIpMessage ackmsg = null;
        while (true) {
            try {
                socket = new Socket();
                socket.setSoTimeout(50000);
                socket.connect(new InetSocketAddress("127.0.0.1", 58001), 50000);

                TcpIpMessage msg = new TcpIpMessage("das5d4a56f458awdf", 1, "客户端发送信息");

                oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(msg);

                ois = new ObjectInputStream(socket.getInputStream());
                while ((ackmsg = (TcpIpMessage) ois.readObject()) != null) {
                    System.out.println(ackmsg.toString());
                    break;
                }
            } catch (Exception e) {
                logger.error("Exception in POSKDSRequest!!", e);
            } finally {
                if (ois != null) {
                    try {
                        ois.close();
                    } catch (Exception e) {
                        logger.error("Exception!!", e);
                    }
                }
                if (oos != null) {
                    try {
                        oos.close();
                    } catch (Exception e) {
                        logger.error("Exception!!", e);
                    }
                }
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (Exception e) {
                        logger.error("Exception!!", e);
                    }
                }
            }
        }
    }

}
