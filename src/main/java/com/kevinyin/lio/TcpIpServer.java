package com.kevinyin.lio;

import com.kevinyin.lsocket.TcpIpMessage;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Kevin_Yin on 2017/6/30.
 */
@Slf4j
public class TcpIpServer {

    private static ExecutorService poolService = Executors.newSingleThreadExecutor();


    private ArrayList<String> msgIds = new ArrayList<String>();
    private ArrayList<TcpIpMessage> msgs = new ArrayList<TcpIpMessage>();

    private static final int TIMEOUT = 50000;

    private int port;

    public TcpIpServer(int port) {
        this.port = port;
    }

    public void run() {
        ServerSocket server = null;
        try {
            server = new ServerSocket();
            server.bind(new InetSocketAddress("127.0.0.1", port));

            while (true) {
                Socket socket = null;
                ObjectInputStream ois = null;
                try {
                    socket = server.accept();
                    log.info("Server got request==========port = " + port);
                    socket.setSoTimeout(TIMEOUT);
                    ois = new ObjectInputStream(socket.getInputStream());
                    TcpIpMessage message = (TcpIpMessage) ois.readObject();
                    if (message != null) {
                        poolService.submit(new ServerThread(this, message, socket, ois));
                    }
                } catch (Exception e) {
                    log.error("Exception on Server", e);
                } finally {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final int MSG_REQ_MAX = 200;

    public void reduceMsg() {
        int size = msgIds.size();
        if (size > MSG_REQ_MAX) {
            ArrayList<String> tempMsgIds = new ArrayList<String>();
            ArrayList<TcpIpMessage> tempMsgs = new ArrayList<TcpIpMessage>();
            for (int i = MSG_REQ_MAX / 2; i < size; i++) {
                tempMsgIds.add(msgIds.get(i));
                tempMsgs.add(msgs.get(i));
            }
            msgIds = tempMsgIds;
            msgs = tempMsgs;
        }
    }

    public boolean containsID(String msgID) {
        return msgIds.contains(msgID);
    }

    public void addID(String id) {
        msgIds.add(id);
    }

    public void addMessage(TcpIpMessage msg) {
        msgs.add(msg);
    }

    public void persistMsgIDs() {
        String path = "msgs/";
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String idsFileName = path + "_" + this.port + ".id";
        String msgFileName = path + "_" + this.port + ".msg";
        try {
            ServiceAgentToolkit.writeData2File(idsFileName, ServiceAgentToolkit.serialize(this.msgIds));
            ServiceAgentToolkit.writeData2File(msgFileName, ServiceAgentToolkit.serialize(this.msgs));
        } catch (Exception e) {
            log.error("Persist msgIDs / Msgs to file got exception: ", e);
        }
    }

}
