package com.kevinyin.lio;

import com.kevinyin.lsocket.TcpIpMessage;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.UUID;

/**
 * Created by Kevin_Yin on 2017/6/30.
 */
public class ServerThread extends Thread {

    private Integer type;
    TcpIpMessage message;
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    TcpIpServer tcpIpServer;

    public ServerThread(TcpIpServer server, TcpIpMessage message , Socket socket, ObjectInputStream ois) {
        this.type = type;
        this.message = message;
        this.socket = socket;
        this.ois = ois;
        this.oos = oos;
        this.tcpIpServer = server;
    }

    public void run(){
        System.out.println("Call Start......");
        tcpIpServer.addMessage(message);
        tcpIpServer.reduceMsg();
        tcpIpServer.addID(message.getUuid());
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            File filePath = new File("files");
            if (!filePath.exists()){
                filePath.mkdir();
            }
            File file = new File(filePath, UUID.randomUUID().toString()+".tmp");
            file.createNewFile();
            ServiceAgentToolkit.writeData2File(file.getAbsolutePath(),message.getMessage().getBytes());

            TcpIpMessage ackmsg = new TcpIpMessage("125454", 10, "服务器");
            System.out.println("服务器写会数据之前。。。。");
            oos.writeObject(ackmsg);
            System.out.println("服务器写会数据之后。。。。");
            oos.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            tcpIpServer.persistMsgIDs();
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
