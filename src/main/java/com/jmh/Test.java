package com.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author zxw
 * @date 2019/9/3 11:43
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class Test {
    static Map map  = new HashMap();
    static Map syncMap = Collections.synchronizedMap(new HashMap<>());
    static ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
    @Setup
    public void setup(){
        for (int i = 0; i < 10000; i++) {
            map.put(Integer.toString(i),Integer.toString(i));
            syncMap.put(Integer.toString(i),Integer.toString(i));
            concurrentHashMap.put(Integer.toString(i),Integer.toString(i));
        }
    }

    @Benchmark
    public void hashMapGet(){
        map.get("4");
    }

    @Benchmark
    public void syncGet(){
        syncMap.get("4");
    }

    @Benchmark
    public void concurrentGet(){
        syncMap.get("4");
    }

    @Benchmark
    public void hashMapSize(){
        syncMap.size();
    }

    @Benchmark
    public void syncSize(){
        syncMap.size();
    }

    @Benchmark
    public void concurrentSize(){
        syncMap.size();
    }

    public static void main(String[] args) throws RunnerException {
        Options option = new OptionsBuilder().include(Test.class.getSimpleName()).forks(4).build();
        new Runner(option).run();
    }
}
