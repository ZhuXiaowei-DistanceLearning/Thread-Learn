package com.java8;

import java.util.concurrent.locks.StampedLock;

/**
 * @author zxw
 * @date 2019/8/29 17:19
 */
public class Point {
    private double x, y;
    private final StampedLock s1 = new StampedLock();

    // 排它锁
    void move(double deltaX, double deltaY) {
        long stamp = s1.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            s1.unlockWrite(stamp);
        }
    }

    // 只读
    double distanceFromOrigin() {
        long l = s1.tryOptimisticRead();
        double currentX = x, currentY = y;
        // 判断乐观锁是否在读的过程期间被修改
        if (!s1.validate(l)) {
            l = s1.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                s1.unlockRead(l);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }
}
