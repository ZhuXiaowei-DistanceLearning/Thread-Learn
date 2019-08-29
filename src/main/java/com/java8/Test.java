package com.java8;

import javafx.stage.Stage;

import java.nio.file.DirectoryStream;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

/**
 * @author zxw
 * @date 2019/8/29 16:57
 */
public class Test {
    static int[] arr = {1, 3, 4, 5, 6, 7, 8, 9, 10};
    static int[] arr2 = {1,5,2,6,7,4,6,3};


    public static void main(String[] args) {
//        Arrays.stream(arr).forEach(System.out::println);
//        Arrays.stream(arr).forEach(new IntConsumer() {
//            @Override
//            public void accept(int value) {
//                System.out.println(value);
//            }
//        });
//        Arrays.parallelSort(arr2);
//        Arrays.stream(arr2).forEach(System.out::println);
//
//        long l1 = System.currentTimeMillis();
//        long count = IntStream.range(1, 1000000).parallel().filter(PrimeUtil::ispRrime).count();
//        long l2 = System.currentTimeMillis();
//        System.out.println(count);
//        System.out.println(l2-l1);

    }
}
