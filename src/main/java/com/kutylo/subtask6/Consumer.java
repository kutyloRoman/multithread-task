package com.kutylo.subtask6;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

@Getter
@Slf4j
public class Consumer {

  long queueTime;
  long poolTime;
  long poolCountOfOperation = 0;
  long queueCountOfOperation = 0;

  public void consumeDateFromPool(BlockingPool<Integer> blockingPool, AtomicBoolean running) {
    long poolStartTime = System.currentTimeMillis();
    while (running.get()) {
      try {
        Integer value = blockingPool.get();
        if (value != null) {
          poolCountOfOperation++;
          log.info("square root of number: {} equal {}", value, value * value);
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    poolTime = System.currentTimeMillis() - poolStartTime;
  }

  public void consumeDateFromQueue(BlockingQueue<Integer> blockingQueue, AtomicBoolean running) {
    long queueStartTime = System.currentTimeMillis();
    while (running.get()) {
      Integer value = blockingQueue.poll();
      if (value != null) {
        queueCountOfOperation++;
        log.info("square root of number: {} equal {}", value, value * value);
      }
    }
    queueTime = System.currentTimeMillis() - queueStartTime;
  }

}
