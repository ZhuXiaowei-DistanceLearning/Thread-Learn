package com.java8;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.StampedLock;

/**
 * @author zxw
 * @date 2019/8/29 17:26
 */
public class StampedLockPCDemo {
    static Thread[] threads = new Thread[3];
    static final StampedLock lock = new StampedLock();

    public static void main(String[] args) throws InterruptedException {
        new Thread() {
            @Override
            public void run() {
                long readLong = lock.writeLock();
                //
                LockSupport.parkNanos(60000000000L);
                lock.unlockWrite(readLong);
            }
        }.start();
        Thread.sleep(100);
        for (int i = 0; i < 3; i++) {
            threads[i] = new Thread(new HoldCPUReadThread());
            threads[i].start();
        }
        Thread.sleep(10000);
        for (int i = 0; i < 3; i++) {
            threads[i].interrupt();
        }
    }

    private static class HoldCPUReadThread implements Runnable {

        @Override
        public void run() {
            long lockr = lock.readLock();
            System.out.println(Thread.currentThread().getName() + "获得锁");
            lock.unlock(lockr);
        }
    }
}
