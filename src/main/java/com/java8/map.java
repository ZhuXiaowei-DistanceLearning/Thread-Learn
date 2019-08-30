package com.java8;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zxw
 * @date 2019/8/30 9:03
 */
public class map {

    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        for (int i = 1; i <= 100; i++) {
            map.put(Integer.toString(i), i);
        }
        int count = map.reduceValues(2, (i, j) -> i + j);
        System.out.println(map.get("1"));
        Integer put = map.put("1", 2);
        System.out.println(put);
        System.out.println(map.get("1"));
    }

}
