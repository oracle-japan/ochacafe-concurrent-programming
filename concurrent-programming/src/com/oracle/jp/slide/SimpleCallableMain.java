package com.oracle.jp.slide;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class SimpleCallableMain {
    public static void main(String[] args) throws Exception {
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        Future<String> result = singleThreadPool.submit(new SimpleCallableTask());
        System.out.println(result.get());
        singleThreadPool.shutdown();
        if (!singleThreadPool.awaitTermination(1, TimeUnit.SECONDS)) {
            singleThreadPool.shutdownNow();
        }
    }
}
