package com.kutylo.subtask3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class MessageBroker {

  public void execute() throws InterruptedException {

    List<String> topics = Arrays.asList("one", "two", "three");
    Map<String, Queue<Integer>> map = new HashMap<>();
    map.put(topics.get(0), new PriorityQueue<>());
    map.put(topics.get(1), new PriorityQueue<>());
    map.put(topics.get(2), new PriorityQueue<>());

    Thread producer1 = new Thread(new Producer(map, topics));
    Thread producer2 = new Thread(new Producer(map, topics));
    Thread consumer1 = new Thread(new Consumer(map, topics.get(0)));
    Thread consumer2 = new Thread(new Consumer(map, topics.get(1)));
    Thread consumer3 = new Thread(new Consumer(map, topics.get(0)));
    Thread consumer4 = new Thread(new Consumer(map, topics.get(2)));

    producer1.start();
    producer2.start();
    consumer1.start();
    consumer2.start();
    consumer3.start();
    consumer4.start();

    Thread.sleep(3000);
  }

}
