package com.Ats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author zxw
 * @date 2019/8/30 14:00
 * 并行程序
 */
public class UnsafeArrayList {
//    static Vector al = new Vector();
//    static List al = Collections.synchronizedList(new ArrayList<>());
    static CopyOnWriteArrayList al = new CopyOnWriteArrayList();
    static class AddTask implements Runnable {

        @Override
        public void run() {
//            synchronized (al) {
//                for (int i = 0; i < 1000000; i++) {
//                    al.add(i);
//                }
//            }
//            al.wait();
            for (int i = 0; i < 1000000; i++) {
                al.add(i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long l1 = System.currentTimeMillis();
        Thread t1 = new Thread(new AddTask(), "t1");
        Thread t2 = new Thread(new AddTask(), "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        long l2 = System.currentTimeMillis();
        System.out.println(al.size());
        System.out.println(l2 - l1);
    }
}
