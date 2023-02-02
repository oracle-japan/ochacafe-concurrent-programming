package com.oracle.jp.demo.demo3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import com.oracle.jp.demo.utils.FizzBuzzTask;
import com.oracle.jp.demo.utils.FizzBuzzUtils;

public class VirtualThreadMoreSleepMain {
  private static final Logger logger = Logger.getLogger(VirtualThreadMoreSleepMain.class.getName());

  public static void main(String[] args) {
    long start = System.currentTimeMillis();

    // Demo 1
    ThreadFactory virtualThreadFactory = Thread.ofVirtual().name("virtual-thread-", 1).factory();
    createThreadPerExecFizzBuzz(virtualThreadFactory);

    // Demo 2
    // ThreadFactory platformThreadFactory = Thread.ofPlatform().name("platform-thread-", 1).factory();
    // createThreadPerExecFizzBuzz(platformThreadFactory);

    long end = System.currentTimeMillis();
    logger.info("Execution time: " + Long.toString(end - start) + "[ms]");
  }

  public static void createThreadPerExecFizzBuzz(ThreadFactory factory) {
    FizzBuzzTask task = new FizzBuzzTask();
    try (ExecutorService exector = Executors.newThreadPerTaskExecutor(factory)) {
      IntStream.rangeClosed(1, 1_000_000).forEach(i -> {
        exector.submit(() -> {
          task.execWithSleep(i, 10_000);
          FizzBuzzUtils.log(i, task.exec(i));
        });
      });
    }
  }

}
