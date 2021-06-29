package com.kutylo.subtask1;

import org.junit.Test;

import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class DasExperimentHCTest {

    private final DasExperiment dasExperiment = new DasExperiment();

    @Test(expected = ConcurrentModificationException.class)
    public void testExperiment() throws InterruptedException {
        dasExperiment.executeExperiment(new HashMap<>());
    }

    @Test
    public void testExperimentWithConcurrentMap() throws InterruptedException {
        dasExperiment.executeExperiment(new ConcurrentHashMap<>());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testExperimentWithSynchronizedMap() throws InterruptedException {
        dasExperiment.executeExperiment(Collections.synchronizedMap(new HashMap<>()));
    }

}
