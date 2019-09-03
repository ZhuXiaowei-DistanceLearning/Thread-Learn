package com.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author zxw
 * @date 2019/8/26 16:44
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class JMHSample_01_HelloWorld {

    CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
    ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
    CopyOnWriteArrayList copyOnWriteArrayList2 = new CopyOnWriteArrayList();
    ConcurrentLinkedQueue concurrentLinkedQueue2 = new ConcurrentLinkedQueue();
    @Setup
    public void setup(){
        for (int i = 0; i < 10; i++) {
            copyOnWriteArrayList.add(new Object());
            concurrentLinkedQueue.add(new Object());
        }

        for (int i = 0; i < 1000; i++) {
            copyOnWriteArrayList2.add(new Object());
            concurrentLinkedQueue2.add(new Object());
        }
    }

    @Benchmark
    public void copyonWriteGet(){
        copyOnWriteArrayList.get(0);
    }

    @Benchmark
    public void copyonWriteSize(){
        copyOnWriteArrayList.size();
    }

    @Benchmark
    public void cononWriteGet(){
        concurrentLinkedQueue.peek();
    }

    @Benchmark
    public void conWriteSsize(){
        concurrentLinkedQueue.size();
    }

    @Benchmark
    public void copyonWriteWrite(){
        copyOnWriteArrayList.add(new Object());
        copyOnWriteArrayList.remove(0);
    }

    @Benchmark
    public void bigWirte(){
        copyOnWriteArrayList2.add(new Object());
        copyOnWriteArrayList2.remove(0);
    }

    @Benchmark
    public void bigWitecon(){
       concurrentLinkedQueue2.offer(new Object());
       concurrentLinkedQueue2.remove(0);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void measureThroughput() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }



    public static void main(String[] args) throws RunnerException, InterruptedException {
//        Options opt = new OptionsBuilder().include(JMHSample_01_HelloWorld.class.getSimpleName()).forks(4).build();
//        new Runner(opt).run();
        JMHSample_01_HelloWorld s = new JMHSample_01_HelloWorld();
        s.measureThroughput();
    }
}
