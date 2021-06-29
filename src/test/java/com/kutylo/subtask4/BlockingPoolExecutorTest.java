package com.kutylo.subtask4;

import org.junit.Test;

public class BlockingPoolExecutorTest {

    private BlockingPoolExecutor blockingPoolExecutor = new BlockingPoolExecutor();

    @Test
    public void testBlockingPool() throws InterruptedException {
        blockingPoolExecutor.execute();
    }

}
