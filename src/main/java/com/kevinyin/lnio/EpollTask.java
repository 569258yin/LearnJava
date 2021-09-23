package com.kevinyin.lnio;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * Created by YH on 2017/6/29.
 */
@Slf4j
public class EpollTask {
    private SocketChannel socketChannel;
    private SelectionKey key;
    private int state;


    private NioMessage resultMsg = new NioMessage(-1, "Exception Error");

    public EpollTask(SocketChannel socketChannel, SelectionKey key) {
        this.socketChannel = socketChannel;
        this.key = key;
    }

    public void onRead(ByteBuffer buffer) {
        ObjectInputStream in = null;
        try {
            ByteArrayInputStream bIn = new ByteArrayInputStream(buffer.array());
            in = new ObjectInputStream(bIn);
            NioMessage msg = (NioMessage) in.readObject();
            handleMsg(msg);
        } catch (IOException e) {
            log.error("Server Read IO Exception!", e);
        } catch (ClassNotFoundException e) {
            log.error("Server ClassNotFoundException!", e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onWrite() {
        ObjectOutputStream out = null;
        try {
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            out = new ObjectOutputStream(bOut);
            out.writeObject(resultMsg);
            out.flush();
            byte[] arr = bOut.toByteArray();

            System.out.println("Object in " + arr.length + " bytes");
            ByteBuffer bb = ByteBuffer.wrap(arr);
            bb.flip();
            socketChannel.write(bb);

            socketChannel.close();
            key.cancel();
        } catch (IOException e) {
            log.error("Server Write IO Exception!", e);
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleMsg(NioMessage message) {
        switch (message.getType()) {
            case 0:
                resultMsg.setMessage("我接收到了0类型数据");
            case 1:
                resultMsg.setMessage("我接收到了1类型数据");
                break;
            case 2:
                resultMsg.setMessage("我接收到了2类型的数据");
        }
    }
}
