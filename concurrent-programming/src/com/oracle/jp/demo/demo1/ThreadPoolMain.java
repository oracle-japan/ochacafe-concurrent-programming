package com.oracle.jp.demo.demo1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

import com.oracle.jp.demo.utils.FizzBuzzCallableTask;

public class ThreadPoolMain {
    public static void main(String[] args) {
        ThreadPoolMain main = new ThreadPoolMain();

        // 1 スレッドを持つスレッドプールを作成
        // ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        // main.exec(singleThreadPool);

        // 3 スレッドを持つスレッドプールを作成
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        main.exec(fixedThreadPool);

        // 新しいスレッドの作成が必要であれば作成、必要なければプール済みのスレッドを再利用
        // ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        // main.exec(cachedThreadPool);
    }

    private void exec(ExecutorService threadPool) {
        List<Future<Boolean>> submitTaskList = new ArrayList<>();
        IntStream.rangeClosed(1, 100).forEach(i -> {
            submitTaskList.add(threadPool.submit(new FizzBuzzCallableTask(i)));
        });

        submitTaskList.forEach(task -> {
            try {
                if (task.get() == false) {
                    throw new RuntimeException("Error in Thread.");
                }
            } catch (CancellationException | ExecutionException | InterruptedException e) {
                throw new RuntimeException("Error in Thread.");
            }
        });

        threadPool.shutdown();
    }
}
