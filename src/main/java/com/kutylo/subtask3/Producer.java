package com.kutylo.subtask3;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

@Slf4j
public class Producer implements Runnable {

  private Map<String, Queue<Integer>> queueMap;
  private List<String> topics;

  public Producer(Map<String, Queue<Integer>> queueMap, List<String> topics) {
    this.queueMap = queueMap;
    this.topics = topics;
  }

  @Override
  public void run() {
    while (true) {
      String topic = topics.get(new Random().nextInt(topics.size()));
      Integer value = new Random().nextInt(100);
      queueMap.get(topic).add(value);
      log.info("produce: topic-{} - {}", topic, value);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
