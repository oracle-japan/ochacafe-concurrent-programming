package com.oracle.jp.demo.demo2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import com.oracle.jp.demo.utils.FizzBuzzTask;
import com.oracle.jp.demo.utils.FizzBuzzUtils;

public class DemoVirtualThreads {
  private static final Logger logger = Logger.getLogger(DemoVirtualThreads.class.getName());

  public static void main(String[] args) {
    long start = System.currentTimeMillis();

    // Demo 1
    // ThreadFactory platformThreadFactory = Thread.ofPlatform().name("platform-thread-", 1).factory();
    // createThreadPerExecFizzBuzz(platformThreadFactory);

    // Demo 2
    ThreadFactory virtualThreadFactory = Thread.ofVirtual().name("virtual-thread-", 1).factory();
    createThreadPerExecFizzBuzz(virtualThreadFactory);

    long end = System.currentTimeMillis();
    logger.info("Execution time: " + Long.toString(end - start) + "[ms]");
  }

  public static void createThreadPerExecFizzBuzz(ThreadFactory factory) {
    FizzBuzzTask task = new FizzBuzzTask();
    try (ExecutorService exector = Executors.newThreadPerTaskExecutor(factory)) {
      IntStream.rangeClosed(1, 10_000).forEach(i -> {
        exector.submit(() -> {
          task.execWithSleep(i, 1_000);
          FizzBuzzUtils.log(i, task.exec(i));
        });
      });
    }
  }

}
