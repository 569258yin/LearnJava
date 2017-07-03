package com.kevinyin.lsocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.Callable;

/**
 * Created by Kevin_Yin on 2017/6/30.
 */
public class ServerThread extends Thread {

    private Integer type;
    private String message;
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;


    public ServerThread(Integer type, String message, Socket socket, ObjectInputStream ois) {
        this.type = type;
        this.message = message;
        this.socket = socket;
        this.ois = ois;
        this.oos = oos;
    }

    public void run(){
        System.out.println("Call Start......");
        try {
            Thread.sleep(60000);
            oos = new ObjectOutputStream(socket.getOutputStream());
            TcpIpMessage ackmsg = new TcpIpMessage("125454", 10, "服务器");
            System.out.println("服务器写会数据之前。。。。");
            oos.writeObject(ackmsg);
            System.out.println("服务器写会数据之后。。。。");
            oos.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
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
