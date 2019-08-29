package com.java8;

/**
 * @author zxw
 * @date 2019/8/29 17:02
 */
public class PrimeUtil {
    public static boolean ispRrime(int number) {
        int tmp = number;
        if (tmp < 2) {
            return false;
        }
        for (int i = 2; Math.sqrt(tmp) >= i; i++) {
            if (tmp % i == 0) {
                return false;
            }
        }
        return true;
    }
}
