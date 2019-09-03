package com.jmh;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

/**
 * @author zxw
 * @date 2019/9/3 11:37
 */
@State(Scope.Benchmark)
public class StateTest {
    volatile double x = Math.PI;

    @State(Scope.Thread)
    public static class ThreadState{
        volatile double x = Math.PI;
    }
}


