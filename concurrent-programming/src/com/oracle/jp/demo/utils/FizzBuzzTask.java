package com.oracle.jp.demo.utils;

import java.util.concurrent.TimeUnit;

public class FizzBuzzTask {
    public String exec(int input) {
        switch (input % 15) {
            case 0:
                return "FizzBuzz";
            case 3:
            case 6:
            case 9:
            case 12:
                return "Fizz";
            case 5:
            case 10:
                return "Buzz";
            default:
                return Integer.toString(input);
        }
    }

    public String execWithSleep(int input, long ms) {
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
            return exec(input);
        } catch (InterruptedException e) {
            // error handle.
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

}
