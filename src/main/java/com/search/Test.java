package com.search;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zxw
 * @date 2019/8/28 21:18
 */
public class Test {
    static int[] arr;
    static ExecutorService es = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());
    static AtomicInteger result = new AtomicInteger(-1);

    public static class SearchTask implements Callable<Integer> {
        int begin, end, searchValue;

        public SearchTask(int begin, int end, int searchValue) {
            this.begin = begin;
            this.end = end;
            this.searchValue = searchValue;
        }

        @Override
        public Integer call() {
            int re = search(searchValue, begin, end);
            return re;
        }
    }

    public static void main(String[] args) {
        int i = 0;
    }

    public static int search(int searchValue, int beiginPos, int endPos) {
        int i = 0;
        for (i = beiginPos; i < endPos; i++) {
            if (result.get() > 0) {
                return result.get();
            }
            if (arr[i] == searchValue) {
                if (!result.compareAndSet(-1, i)) {
                    return result.get();
                }
                return i;
            }
        }
        return -1;
    }
}
