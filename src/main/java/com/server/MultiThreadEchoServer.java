package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.Selector;
import java.util.concurrent.*;

/**
 * @author zxw
 * @date 2019/8/29 8:57
 */
public class MultiThreadEchoServer {
    private Selector selector;
    private static ExecutorService tp = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.MILLISECONDS, new SynchronousQueue<>());

    static class HandleMsg implements Runnable {
        Socket clientSocket;

        public HandleMsg(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            BufferedReader is = null;
            PrintWriter os = null;
            try {
                is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                // 从InputStream中读取客户端发送的数据
                String inputLine = null;
                long b = System.currentTimeMillis();
                while ((inputLine = is.readLine()) != null) {
                    os.println(inputLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        serverSocket = new ServerSocket(8000);
        while (true) {
            clientSocket = serverSocket.accept();
            System.out.println(clientSocket.getRemoteSocketAddress() + "connect");
            tp.execute(new HandleMsg(clientSocket));
        }
    }

}
