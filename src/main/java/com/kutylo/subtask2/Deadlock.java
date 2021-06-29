package com.kutylo.subtask2;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

@Slf4j
public class Deadlock {

  private List<Long> numbers = new ArrayList<>();
  private final Object monitor = new Object();
  private final Semaphore semaphore = new Semaphore(1);
  private boolean flag = false;

  public void execute() throws InterruptedException {
    Thread sourceThread = new Thread(() -> {
      while (true) {
        synchronized (monitor) {
          if (flag) {
            log.info("Wait source");
            flag = false;
            monitor.notifyAll();
            try {
              monitor.wait();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            log.info("Flag in source: {}", flag);
            log.info("Generate new number");
            numbers.add(new Random().nextLong());
            Deadlock.sleep(3000);
          }
        }
      }
    });
    Thread sumThread = new Thread(() -> {
      while (true) {
        synchronized (monitor) {
          if (!flag) {
            flag = true;
            log.info("Wait sum");
            try {
              monitor.wait();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            log.info("Flag in sum: {}", flag);
            flag = false;
            log.info("sum: " + numbers.stream().reduce(Long::sum).orElse(0L));
            Deadlock.sleep(3000);
            monitor.notifyAll();
          }
        }
      }

    });
    Thread squareRootThread = new Thread(() -> {
      while (true) {
        synchronized (monitor) {
          if (!flag) {
            flag = true;
            log.info("Wait square root");
            try {
              monitor.wait();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            log.info("Flag in square root: {}", flag);
            log.info("square root: " + numbers.stream()
                .reduce(0L, (n1, n2) -> n1 * n1 + n2 * n2));
            Deadlock.sleep(3000);
            monitor.notifyAll();
          }
        }
      }
    });

    sourceThread.start();
    sumThread.start();
    squareRootThread.start();

    sourceThread.join();
  }

  static void sleep(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
