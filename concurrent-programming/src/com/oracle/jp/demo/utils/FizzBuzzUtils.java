package com.oracle.jp.demo.utils;

import java.util.concurrent.TimeUnit;

public class FizzBuzzUtils {

    private FizzBuzzUtils() {
    }

    public static void log(int i, String message) {
        System.out.println(String.format("[%s]: %s - %s", Thread.currentThread().getName(), i, message));
    }

    public static void sleep(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }

}
