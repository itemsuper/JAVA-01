package io.github.itemsuper.gateway;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 创建了一个固定大小的线程池处理请求
public class BackendServer {
    public static void main(String[] args) throws IOException{
        System.out.println("可用的计算资源数："+Runtime.getRuntime().availableProcessors());
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors() + 2);
        final ServerSocket serverSocket = new ServerSocket(8088);
        while (true) {
            try {
                System.out.println("-------access-------");
                final Socket socket = serverSocket.accept();
                System.out.println("接收到客户端的参数");
                System.out.println("===>"+socket.getLocalSocketAddress());
                executorService.execute(() -> service(socket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void service(Socket socket) {
        try {
//            Thread.sleep(5);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "hello,netty";
            printWriter.println("Content-Length:" + body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
            socket.close();
        } catch (IOException e) { // | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
