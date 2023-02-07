package com.oracle.jp.demo.demo1;

import java.util.concurrent.Callable;

public class DemoCallableTask implements Callable<String> {
    private int index;

    /**
     * @param index
     */
    public DemoCallableTask(int index) {
        this.index = index;
    }

    @Override
    public String call() throws Exception {
        return String.format("[%s]: %s", Thread.currentThread().getName(), "Hello world!");
    }

}
