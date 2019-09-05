package com.java8;

/**
 * @author zxw
 * @date 2019/9/5 10:10
 */
@FunctionalInterface
public interface Mydefine {
    int run(int v1,int v2);
    default void aa(){
        System.out.println("aaa");
    };
}
