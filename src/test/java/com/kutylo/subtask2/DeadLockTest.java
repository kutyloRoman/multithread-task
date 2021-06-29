package com.kutylo.subtask2;

import org.junit.Test;

public class DeadLockTest {

    private final Deadlock deadlock = new Deadlock();

    @Test
    public void testDeadLock() throws InterruptedException {
        deadlock.execute();
    }

}
