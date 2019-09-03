package com.pool;

import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Executor;
import java.util.concurrent.RecursiveTask;

/**
 * @author zxw
 * @date 2019/9/3 10:45
 */
public class CountTask extends RecursiveTask<Long> {
    private static final int THRESHOLD = 10000;
    private long start;
    private long end;

    public CountTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        boolean canCompute = (end - start) < THRESHOLD;
        if (canCompute) {
            for (long i = start; i <= end; i++) {
                sum += i;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Executor executor = MoreExecutors.directExecutor();
//        MoreExecutors.getExitingExecutorService(executor);
        executor.execute(() -> System.out.println("I am running in " + Thread.currentThread().getName()));
    }
}
