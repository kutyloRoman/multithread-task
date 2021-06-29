package com.kutylo.subtask1;

import lombok.extern.slf4j.Slf4j;

import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class DasExperiment {

    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    private ConcurrentModificationException exc;

    public void executeExperiment(Map<Integer, Integer> map) throws InterruptedException {
        List<Integer> source = IntStream.range(0, 1000000).boxed().collect(Collectors.toList());

        Thread thread1 = new Thread(() -> {
            log.info(Thread.currentThread().getName() + "start execution");
            source.forEach(s -> map.put(s, s));
        });
        Thread thread2 = new Thread(() -> {
            try {
                log.info(Thread.currentThread().getName() + "start execution");
                log.info(String.valueOf(map.values().stream().reduce(Integer::sum).orElse(0)));
            } catch (ConcurrentModificationException e) {
                exc = e;
            }
        });

        thread1.start();
        thread2.start();

        thread2.join();
        if (exc != null) {
            throw exc;
        }
    }

    public void executeFixedExperiment(Map<Integer, Integer> map) throws InterruptedException {
        List<Integer> source = IntStream.range(0, 10000).boxed().collect(Collectors.toList());

        Thread thread1 = new Thread(() -> {
            log.info(Thread.currentThread().getName() + "start execution");
            source.forEach(s -> map.put(s, s));
            countDownLatch.countDown();
        });
        Thread thread2 = new Thread(() -> {
            try {
                countDownLatch.await();
                log.info(Thread.currentThread().getName() + "start execution");
                log.info(String.valueOf(map.values().stream().reduce(Integer::sum).orElse(0)));
            } catch (ConcurrentModificationException e) {
                exc = e;
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();

        thread2.join();
        if (exc != null) {
            throw exc;
        }
    }

}
