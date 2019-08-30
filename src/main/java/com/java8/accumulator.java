package com.java8;

import java.util.Random;
import java.util.concurrent.atomic.LongAccumulator;

/**
 * @author zxw
 * @date 2019/8/30 9:00
 */
public class accumulator {

    public static void main(String[] args) throws InterruptedException {
        Thread[] ts = new Thread[1000];
        LongAccumulator accumulator = new LongAccumulator(Long::max, Long.MAX_VALUE);
        for (int i = 0; i < 1000; i++) {
            ts[i] = new Thread(() -> {
                Random random = new Random();
                long value = random.nextLong();
                accumulator.accumulate(value);
            });
            ts[i].start();
        }
        for (int i = 0; i < 1000; i++) {
            ts[i].join();
        }

        System.out.println(accumulator.longValue());
        System.out.println(1 << 30);
    }
}
