package com.oracle.jp.jep;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;
import java.util.stream.IntStream;

public class SemaphoreSample {

    public static void main(String[] args) {
        ThreadFactory factory = Thread.ofVirtual().name("virtual-", 0).factory();
        Semaphore semaphore = new Semaphore(2);
        try (ExecutorService executor = Executors.newThreadPerTaskExecutor(factory)) {
            IntStream.rangeClosed(0, 10).forEach(i -> {
                executor.submit(new Logging(semaphore));
            });
        }
    }
}
