package com.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author zxw
 * @date 2019/9/5 9:40
 */
public class FutureMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> future = new FutureTask<>(new RealDatajdk("a"));
        ExecutorService pool = Executors.newFixedThreadPool(1);
        pool.submit(future);
        System.out.println("请求完毕");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据=" + future.get());
    }
}
