package com.oracle.jp.slide;

public class SimpleRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println(String.format("[%s]: %s", Thread.currentThread().getName(), "Hello world!"));
    }

}
