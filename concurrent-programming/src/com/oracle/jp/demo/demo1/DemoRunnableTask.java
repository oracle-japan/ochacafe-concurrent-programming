package com.oracle.jp.demo.demo1;

public class DemoRunnableTask implements Runnable {

    @Override
    public void run() {
        System.out.println(String.format("[%s]: %s", Thread.currentThread().getName(), "Hello world!"));
    }

}
