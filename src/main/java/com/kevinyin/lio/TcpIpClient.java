package com.kevinyin.lio;

import com.kevinyin.lsocket.TcpIpMessage;
import org.apache.log4j.Logger;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.UUID;

/**
 * Created by Kevin_Yin on 2017/6/30.
 */
public class TcpIpClient {
    private static Logger logger = Logger.getLogger(TcpIpClient.class);

    private int port;

    public TcpIpClient(int port) {
        this.port = port;
    }

    public void run() {
        Socket socket = null;
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        TcpIpMessage ackmsg = null;
        while (true) {
            try {
                socket = new Socket();
                socket.setSoTimeout(50000);
                socket.connect(new InetSocketAddress("127.0.0.1", port), 50000);

                TcpIpMessage msg = new TcpIpMessage(""+System.currentTimeMillis(), 1, "客户端发送信息:"+ UUID.randomUUID().toString());

                oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(msg);
                DebugLog.logger.debug("<====>Client send Success by port="+port);
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
