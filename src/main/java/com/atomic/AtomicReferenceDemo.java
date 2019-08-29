package com.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author zxw
 * @date 2019/8/27 14:11
 */
public class AtomicReferenceDemo {
    static AtomicStampedReference<Integer> money = new AtomicStampedReference<>(19,0);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            final int stamp = money.getStamp();
            new Thread(){
                public void run(){
                    while (true){
                        while (true){
                            Integer m = money.getReference();
                            if(m<20){
                                if(money.compareAndSet(m,m+20,stamp,stamp+1)){
                                    System.out.println("余额小于20元，充值成功，余额："+money.getReference());
                                    break;
                                }else{
                                    break;
                                }
                            }
                        }
                    }
                }
            }.start();
        }

    }
}
