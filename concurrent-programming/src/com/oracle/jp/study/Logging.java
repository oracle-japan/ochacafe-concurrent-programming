package com.oracle.jp.study;

import java.util.concurrent.Semaphore;

public class Logging implements Runnable {
    private Semaphore semaphore;

    /**
     * @param semaphoe
     */
    public Logging(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            System.out.println(String.format("%s, %s, %s, %s", Thread.currentThread().getName(),
                    Thread.currentThread().getPriority(), Thread.currentThread().getState().name(),
                    "SEMAPHORE ACQUIRE"));
            semaphore.acquire();
            Thread.sleep(5000);
            System.out.println(String.format("%s, %s, %s, %s", Thread.currentThread().getName(),
                    Thread.currentThread().getPriority(), Thread.currentThread().getState().name(), "Hello world"));
        } catch (InterruptedException e) {
            // do nothing.
        } finally {
            System.out.println(String.format("%s, %s, %s, %s", Thread.currentThread().getName(),
                    Thread.currentThread().getPriority(), Thread.currentThread().getState().name(),
                    "SEMAPHORE RELEASE"));
            semaphore.release();
        }
    }
}
