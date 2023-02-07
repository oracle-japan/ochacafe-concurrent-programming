package com.oracle.jp.jep;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;

public class VirtualThreads {
    public static void main(String[] args) throws Exception {
        ThreadFactory virtualThreadFactory = Thread
                .ofVirtual()
                .name("virtual-thread-", 1)
                .allowSetThreadLocals(false)
                .inheritInheritableThreadLocals(false)
                .factory();
        Semaphore semaphore = new Semaphore(2);
        taskPerCreateThread(virtualThreadFactory, semaphore);
    }

    private static void taskPerCreateThread(ThreadFactory threadFactory, Semaphore semaphore) {
        try (ExecutorService executor = Executors.newThreadPerTaskExecutor(threadFactory)) {
            executor.submit(new Logging(semaphore));
        }
    }
}
