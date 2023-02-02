package com.oracle.jp.demo.utils;

public class FizzBuzzRunnableTask implements Runnable {

    private int number;

    /**
     * @param i
     */
    public FizzBuzzRunnableTask(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        FizzBuzzUtils.log(number, new FizzBuzzTask().exec(number));
    }

}
