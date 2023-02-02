package com.oracle.jp.demo.utils;

import java.util.concurrent.Callable;

public class FizzBuzzCallableTask implements Callable<Boolean> {

    private int number;

    /**
     * @param number
     */
    public FizzBuzzCallableTask(int number) {
        this.number = number;
    }

    @Override
    public Boolean call() throws Exception {
        FizzBuzzUtils.log(number, new FizzBuzzTask().exec(number));
        return true;
    }

}
