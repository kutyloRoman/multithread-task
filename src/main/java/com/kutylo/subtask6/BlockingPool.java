package com.kutylo.subtask6;

import lombok.extern.slf4j.Slf4j;

import java.util.PriorityQueue;
import java.util.Queue;

@Slf4j
public class BlockingPool<T> {

    private final int size;
    private final Queue<T> queue = new PriorityQueue<>();
    private final Object monitor = new Object();

    public BlockingPool(int size) {
        this.size = size;
    }

    public T get() throws InterruptedException {
        synchronized (monitor) {
            if (queue.isEmpty()) {
                monitor.wait();
            }
            T object = queue.poll();
            if (queue.size() == size - 1) {
                monitor.notify();
            }
            return object;
        }
    }

    public void put(T object) throws InterruptedException {
        synchronized (monitor) {
            if (queue.size() == size) {
                monitor.wait();
            }
            queue.add(object);
            if (queue.size() == 1) {
                monitor.notify();
            }
        }
    }

}
