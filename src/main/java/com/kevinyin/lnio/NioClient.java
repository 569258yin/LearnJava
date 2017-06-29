package com.kevinyin.lnio;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Random;

/**
 * Created by Kevin_Yin on 2017/6/29.
 */
public class NioClient {
    public static void main(String[] args) throws IOException {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 58001));
            Random r = new Random();

//            try {
//                Thread.sleep(r.nextInt(5000));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            int type = r.nextInt(2);
            System.out.println(type);

            NioMessage message = new NioMessage(type, "");

            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bOut);
            out.writeObject(message);
            out.flush();
            byte[] arr = bOut.toByteArray();
            System.out.println("Object in " + arr.length + " bytes");
            ByteBuffer writeBuffer = ByteBuffer.wrap(arr);
//            writeBuffer.flip();
//            SocketChannel sc = socketChannel.socket().getChannel();
            socketChannel.write(writeBuffer);

            socketChannel.read(readBuffer);
            readBuffer.flip();

            while (true){
                if (readBuffer.hasRemaining()){
                    ByteArrayInputStream bIn = new ByteArrayInputStream(readBuffer.array());
                    ObjectInputStream in = new ObjectInputStream(bIn);
                    NioMessage msg = (NioMessage) in.readObject();
                    System.out.println(msg.getMessage());
                    break;
                }
            }
            socketChannel.close();
        } catch (Exception e) {
        }
    }
}
