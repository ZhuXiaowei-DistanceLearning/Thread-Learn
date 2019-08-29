package com.provider;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zxw
 * @date 2019/8/28 9:10
 */
public class Producer implements Runnable {
    private volatile boolean isRunning = true;
    private BlockingQueue<Integer> queue;
    private static AtomicInteger count = new AtomicInteger();
    private static final int SLEEPTIME = 1000;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Random r = new Random();
        System.out.println("start producer id=" + Thread.currentThread().getId());
        try {
            while (isRunning) {
                Thread.sleep(r.nextInt(SLEEPTIME));

            }
        }catch(InterruptedException e){
                e.printStackTrace();
            }
    }
}