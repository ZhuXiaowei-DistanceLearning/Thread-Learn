package com.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author zxw
 * @date 2019/8/27 14:11
 */
public class AtomicStampeDemo {
    static AtomicReference<Integer> money = new AtomicReference<>();

    public static void main(String[] args) throws InterruptedException {
        money.set(19);
    }
//        () -> {
//            public void run () {
//                while (true) {
//                    while (true) {
//                        Integer m = money.get();
//                        if (m < 20) {
//                            if (money.compareAndSet(m, m + 20)) {
//                                System.out.println("余额小于20元，充值成功，余额：" + money.get());
//                                break;
//                            } else {
//                                break;
//                            }
//                        }
//                    }
//                }
//            }
//        }.start();
}
