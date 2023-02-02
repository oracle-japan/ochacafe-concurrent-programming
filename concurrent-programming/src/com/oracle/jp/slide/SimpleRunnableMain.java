package com.oracle.jp.slide;

public class SimpleRunnableMain {
    public static void main(String[] args) {
    Thread thread = new Thread(new SimpleRunnable());
    thread.start();
    }

    // public static void main(String[] args) {
    //     Thread thread = new Thread(() -> {
    //         System.out.println(String.format("[%s]: %s", Thread.currentThread().getName(), "Hello world!"));
    //     });
    //     thread.start();
    // }
}
