package com.kutylo.subtask1;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Map;

@Slf4j
public abstract class DasExperimentTest {

    private final DasExperiment dasExperiment = new DasExperiment();
    private final Map<Integer, Integer> map;

    public DasExperimentTest(Map<Integer, Integer> map) {
        this.map = map;
    }

    @Test
    public void testFixedExperiment() throws InterruptedException {
        long startTime = System.nanoTime();
        dasExperiment.executeFixedExperiment(map);
        long endTime = System.nanoTime();
        log.info("{} execution time in milliseconds: {}",
                map.getClass().getName(), (endTime - startTime) / 1000000);
    }

}
