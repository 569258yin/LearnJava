package com.kevinyin.lnio.callbackserver;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * Created by Kevin_Yin on 2017/7/4.
 */
public class CommonClient {

    private SocketChannel clinetSocket;
    private ByteBuffer recvBuffer;
    private SelectionKey key;
    private Callback callback;

    private String msg;

    interface Callback {
        public void onSucceed(int data);
    }

    public CommonClient(SocketChannel clinetSocket, SelectionKey key) {
        this.clinetSocket = clinetSocket;
        this.key = key;

        recvBuffer = ByteBuffer.allocate(8);
        try {
            this.clinetSocket.configureBlocking(false);
            key.interestOps(SelectionKey.OP_WRITE);
        }catch (Exception e){

        }
    }

    public void close(){
        try {
            clinetSocket.close();
            key.cancel();
        }catch (Exception e){

        }
    }

    public void sendMessage(String msg, Callback callback){
        this.callback = callback;
        try {
            recvBuffer.clear();
            recvBuffer.put(msg.getBytes());
            recvBuffer.flip();
            clinetSocket.write(recvBuffer);

            key.interestOps(SelectionKey.OP_READ);
        }catch (Exception e){

        }
    }

    public void onWrite(){
        sendMessage("divident", new Callback() {
            public void onSucceed(int data) {
                final int a = data;
                sendMessage("divident", new Callback() {
                    public void onSucceed(int data) {
                        int b = data;
                        sendMessage(String.valueOf(a / b), null);
                    }
                });
            }
        });
    }

    public void onRead(){

    }
}
