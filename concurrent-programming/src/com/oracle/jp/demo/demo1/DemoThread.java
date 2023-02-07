package com.oracle.jp.demo.demo1;

public class DemoThread {

    // public static void main(String[] args) {
    //     System.out.println(String.format("[%s]: %s", Thread.currentThread().getName(), "Hello world!"));
    //     Thread thread = new Thread(new DemoRunnableTask());
    //     thread.start();
    // }

    // ラムダ式でももちろん同様に実装できる
    public static void main(String[] args) {
        System.out.println(String.format("[%s]: %s", Thread.currentThread().getName(), "Hello world!"));
        Thread thread = new Thread(() -> {
            System.out.println(String.format("[%s]: %s", Thread.currentThread().getName(), "Hello world!"));
        });
        thread.start();
    }

}
