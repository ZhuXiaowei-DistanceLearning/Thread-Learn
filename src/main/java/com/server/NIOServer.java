package com.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class NIOServer {
    public static void main(String[] args) throws IOException {
        // 负责轮询是否有新连接，服务端监测到新的连接之后，不再创建
        Selector selector = Selector.open();
        // 负责轮询连接是否有数据可读
        Selector clientSelector = Selector.open();
        new Thread(()->{
            try {
                ServerSocketChannel listenerChannel = ServerSocketChannel.open();
                listenerChannel.socket().bind(new InetSocketAddress(3333));
                listenerChannel.configureBlocking(false);
                listenerChannel.register(selector, SelectionKey.OP_ACCEPT);
                while (true){
                    // 监测是否有新连接
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
