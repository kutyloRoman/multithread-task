package com.kutylo.subtask6;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class Producer {

  long queueTime;
  long poolTime;
  long poolCountOfOperation = 0;
  long queueCountOfOperation = 0;

  public void produceDateToPool(BlockingPool<Integer> blockingPool, AtomicBoolean running) {
    long poolStartTime = System.currentTimeMillis();
    while (running.get()) {
      try {
        Integer number = new Random().nextInt(25);
        blockingPool.put(number);
        poolCountOfOperation++;
        log.info("produce number: {}", number);
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    poolTime = System.currentTimeMillis() - poolStartTime;
  }

  public void produceDateToQueue(BlockingQueue<Integer> blockingQueue, AtomicBoolean running) {
    long queueStartTime = System.currentTimeMillis();
    while (running.get()) {
      try {
        Integer number = new Random().nextInt(25);
        blockingQueue.put(number);
        queueCountOfOperation++;
        log.info("produce number: {}", number);
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    queueTime = System.currentTimeMillis() - queueStartTime;
  }

}
