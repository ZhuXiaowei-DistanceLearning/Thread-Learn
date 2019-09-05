package com.java8;

import com.google.common.base.Function;
import javafx.stage.Stage;

import java.nio.file.DirectoryStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author zxw
 * @date 2019/8/29 16:57
 */
public class Test {
    static int[] arr = {1, 3, 4, 5, 6, 7, 8, 9, 10};
    static int[] arr2 = {1, 5, 2, 6, 7, 4, 6, 3};
    private String hello;

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    public static int run(int v1, int v2) {
        return v1 + v2;
    }

    public static boolean isNumber(int number){
        return true;
    }

    public static void main(String[] args) {
        Mydefine f1 = (v1, v2) -> {
            return v1 + v2;
        };
        Function<Integer, Boolean> isNumber = Test::isNumber;
        LocalDate date = LocalDate.now();
        LocalDateTime now = LocalDateTime.now();
        LocalTime now1 = LocalTime.now();
        System.out.println(date);
        System.out.println(now);
        System.out.println(now1);
//        Mydefine f2 = Test::run;
//        Runnable aNew = Test::new;
//        IntConsumer intConsumer = System.out::println;
//        IntConsumer errComsumer = System.out::println;
//        Arrays.stream(arr).forEach(intConsumer.andThen(errComsumer));
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
