package com.kevinyin.lnio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by YH on 2017/6/29.
 */
@Slf4j
public class NioServer {


    public static void main(String[] args) {
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress("127.0.0.1", 58001));
            ssc.configureBlocking(false);
            Selector selector = Selector.open();
            ssc.register(selector, SelectionKey.OP_ACCEPT);


            ByteBuffer readBuff = ByteBuffer.allocate(1024);

            while (true) {
                int nReady = selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();

                    if (key.isAcceptable()) {
                        log.debug("Server Accept..");
                        // 创建新的连接，并且把连接注册到selector上，而且，
                        // 声明这个channel只对读操作感兴趣。
                        SocketChannel socketChannel = ssc.accept();
                        socketChannel.configureBlocking(false);
                        SelectionKey connectionKey = socketChannel.register(selector, SelectionKey.OP_READ);
                        connectionKey.attach(new EpollTask(socketChannel, connectionKey));
                    } else if (key.isReadable()) {
                        log.debug("Server Read..");
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        readBuff.clear();
                        socketChannel.read(readBuff);
                        readBuff.flip();


                        EpollTask conn = (EpollTask) key.attachment();
                        conn.onRead(readBuff);

                        key.interestOps(SelectionKey.OP_WRITE);
                    } else if (key.isWritable()) {
                        log.debug("Server Write..");
                        SocketChannel socketChannel = (SocketChannel) key.channel();

                        EpollTask conn = (EpollTask) key.attachment();
                        key.interestOps(SelectionKey.OP_READ);
                        conn.onWrite();
                    }
                }
            }

        } catch (IOException e) {
            log.error("服务器异常", e);
        }
    }
}
