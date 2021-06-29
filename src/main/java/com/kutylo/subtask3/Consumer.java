package com.kutylo.subtask3;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Queue;

@Slf4j
public class Consumer implements Runnable {

  private Map<String, Queue<Integer>> queueMap;
  private String topic;

  public Consumer(Map<String, Queue<Integer>> queueMap, String topic) {
    this.queueMap = queueMap;
    this.topic = topic;
  }

  @Override
  public void run() {
    while (true) {
      Integer value = queueMap.get(topic).poll();
      if (value != null) {
        log.info("consume: topic-{} - {}", topic, value);
      }
    }
  }
}
