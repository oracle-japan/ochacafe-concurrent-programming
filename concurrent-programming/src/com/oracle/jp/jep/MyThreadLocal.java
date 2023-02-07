package com.oracle.jp.jep;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.IntStream;

public class MyThreadLocal {

    public static void main(String[] args) throws InterruptedException {
        MyRunnable task = new MyRunnable();
        ThreadFactory factory = Thread
                .ofVirtual()
                .allowSetThreadLocals(true)
                .name("virtual-thread-", 1)
                .inheritInheritableThreadLocals(true)
                .factory();
        try (ExecutorService executor = Executors.newThreadPerTaskExecutor(factory)) {
            IntStream.rangeClosed(0, 10).forEach(i -> {
                executor.submit(task);
            });
        }
    }

    public static class MyRunnable implements Runnable {

        private ThreadLocal<String> threadLocal = new ThreadLocal<>();
        private InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

        @Override
        public void run() {
            System.out.printf("%s [%s] :%s\n", new Date(), Thread.currentThread().getName(), "start");
            String uuid = UUID.randomUUID().toString();
            threadLocal.set(uuid);
            inheritableThreadLocal.set(uuid);
            try {
                Thread.sleep(2000);
                new Thread(() -> {
                    inheritableThreadLocal.set(UUID.randomUUID().toString());
                    System.out.printf("%s [%s] :%s: %s\n", new Date(), Thread.currentThread().getName(), threadLocal.get(),
                    inheritableThreadLocal.get());
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s [%s] :%s: %s\n", new Date(), Thread.currentThread().getName(), threadLocal.get(),
                    inheritableThreadLocal.get());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s [%s] :%s\n", new Date(), Thread.currentThread().getName(), "stop");
        }
    }

}