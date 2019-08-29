package com.java8;

import org.omg.CORBA.INTERNAL;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author zxw
 * @date 2019/8/29 17:10
 */
public class AskThread implements Runnable {
    CompletableFuture<Integer> re = null;

    public AskThread(CompletableFuture<Integer> re) {
        this.re = re;
    }

    @Override
    public void run() {
        int myRe = 0;
        try {
            myRe = re.get() * re.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(myRe);
    }

    public static Integer cacl(Integer para) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return para * para;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        final CompletableFuture<Integer> future = new CompletableFuture<>();
//        new Thread(new AskThread(future)).start();
//        Thread.sleep(1000);
//        future.complete(60);
        CompletableFuture<Void> fu = CompletableFuture.supplyAsync(() -> cacl(50)).thenApply((i) -> Integer.toString(i)).thenApply((str) -> "\"" + str + "\"").thenAccept(System.out::println);
        fu.get();
    }
}
