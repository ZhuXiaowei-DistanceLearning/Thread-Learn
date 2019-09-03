package com.guava;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author zxw
 * @date 2019/9/3 9:22
 */
public class RateLimiterDemo {
    static RateLimiter limiter = RateLimiter.create(2);
    static int i = 0;
    public static class Task implements Runnable{

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis());
            System.out.println(i);
            i++;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            // 限流
//            limiter.acquire();
            // 令牌
            if(!limiter.tryAcquire()){
                continue;
            }
            Thread thread = new Thread(new Thread(new Task()));
        }
    }
}
