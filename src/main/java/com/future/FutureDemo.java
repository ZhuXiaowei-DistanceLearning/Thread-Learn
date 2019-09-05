package com.future;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * @author zxw
 * @date 2019/9/5 9:49
 */
public class FutureDemo {
    public static void main(String[] args) throws InterruptedException {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture<String> task = service.submit(new RealDatajdk("x"));
        task.addListener(()->{
            System.out.println("异步处理成功");
            try {
                System.out.println(task.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        },MoreExecutors.directExecutor());
        System.out.println("main task done ......");
        Thread.sleep(3000);
    }
}
