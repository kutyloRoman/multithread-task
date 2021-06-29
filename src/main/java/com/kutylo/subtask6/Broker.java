package com.kutylo.subtask6;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class Broker {

  public void execute() throws InterruptedException {

    Producer producer = new Producer();
    Consumer consumer = new Consumer();

    BlockingPool<Integer> blockingPool = new BlockingPool<>(10);
    BlockingQueue<Integer> blockingQueue = new PriorityBlockingQueue<>();

    AtomicBoolean running = new AtomicBoolean(true);

    Thread poolProducer = new Thread(() -> producer.produceDateToPool(blockingPool, running));
    Thread queueProducer = new Thread(() -> producer.produceDateToQueue(blockingQueue, running));

    Thread poolConsumer = new Thread(() -> consumer.consumeDateFromPool(blockingPool, running));
    Thread queueConsumer = new Thread(() -> consumer.consumeDateFromQueue(blockingQueue, running));

    poolProducer.start();
    queueProducer.start();

    poolConsumer.start();
    queueConsumer.start();

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      log.info("running shutdown");
      running.set(false);
      try {
        poolProducer.join();
        poolConsumer.join();
        queueProducer.join();
        queueConsumer.join();
        log.info("Producer queue - operation: {}, time: {}",
            producer.queueCountOfOperation, producer.queueTime);
        log.info("Consumer queue - operation: {}, time: {}",
            consumer.queueCountOfOperation, consumer.queueTime);
        log.info("Producer pool - operation: {}, time: {}",
            producer.poolCountOfOperation, producer.poolTime);
        log.info("Consumer pool - operation: {}, time: {}",
            consumer.poolCountOfOperation, consumer.poolTime);
        log.info("Queue ops/sec : {}", (producer.queueCountOfOperation / (producer.queueTime / 1000)));
        log.info("Pool ops/sec : {}", (producer.poolCountOfOperation / (producer.poolTime / 1000)));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }));

    Thread.sleep(4000);
  }

}
