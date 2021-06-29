package com.kutylo.subtask4;

import java.util.Random;

public class BlockingPoolExecutor {

  public void execute() throws InterruptedException {
    BlockingPool blockingPool = new BlockingPool(3);

    Thread thread1 = new Thread(() -> {
      while (true) {
        try {
          blockingPool.put(new Random().nextInt(100));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        sleep(8000);
      }
    });

    Thread thread2 = new Thread(() -> {
      while (true) {
        try {
          blockingPool.get();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        sleep(2000);
      }
    });

    thread1.start();
    thread2.start();

    Thread.sleep(3000);
  }

  private void sleep(long time) {
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
