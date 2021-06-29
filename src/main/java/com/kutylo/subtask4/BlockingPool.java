package com.kutylo.subtask4;

import lombok.extern.slf4j.Slf4j;

import java.util.PriorityQueue;
import java.util.Queue;

@Slf4j
public class BlockingPool {

  private final int size;
  private final Queue<Object> queue = new PriorityQueue<>();
  private final Object monitor = new Object();

  public BlockingPool(int size) {
    this.size = size;
  }

  public Object get() throws InterruptedException {
    synchronized (monitor) {
      log.info("In get method");
      if (queue.isEmpty()) {
        monitor.wait();
      }
      log.info("get object");
      Object object = queue.poll();
      if (queue.size() == size - 1) {
        monitor.notify();
      }
      return object;
    }
  }

  public void put(Object object) throws InterruptedException {
    synchronized (monitor) {
      log.info("In take method");
      if (queue.size() == size) {
        monitor.wait();
      }
      log.info("take object");
      queue.add(object);
      if (queue.size() == 1) {
        monitor.notify();
      }
    }
  }

}
