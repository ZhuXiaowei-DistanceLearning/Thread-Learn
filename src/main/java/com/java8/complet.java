package com.java8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author zxw
 * @date 2019/9/6 9:17
 */
public class complet {
    public static Integer cacl(Integer para) {
        try {
            Thread.sleep(1000);
            System.out.println(para * para);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return para * para;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> cacl(50)).exceptionally(ex->{
            System.out.println(ex.toString());
            return 0;
        })
                .thenCompose((i)->CompletableFuture.supplyAsync(()->cacl(i)))
                .thenApply((i)->Integer.toString(i))
                .thenAccept(System.out::println);
        System.out.println("开始");
        future.get();
        System.out.println("结束");
    }
}
