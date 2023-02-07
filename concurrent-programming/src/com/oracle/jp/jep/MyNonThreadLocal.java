package com.oracle.jp.jep;

import java.util.Date;
import java.util.UUID;

public class MyNonThreadLocal {

    public static class MyRunnable implements Runnable {

        private String threadLocal = null;

        @Override
        public void run() {
            System.out.printf("%s [%s] :%s\n", new Date(), Thread.currentThread().getName(), "start");
            threadLocal = UUID.randomUUID().toString();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s [%s] :%s\n", new Date(), Thread.currentThread().getName(), threadLocal);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s [%s] :%s\n", new Date(), Thread.currentThread().getName(), "stop");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyRunnable task = new MyRunnable();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(task);
            thread.start();
        }
    }
}