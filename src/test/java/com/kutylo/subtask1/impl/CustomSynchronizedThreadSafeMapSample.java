package com.kutylo.subtask1.impl;


import com.kutylo.subtask1.CustomSynchronizedThreadSafeMap;
import com.kutylo.subtask1.DasExperimentTest;

public class CustomSynchronizedThreadSafeMapSample extends DasExperimentTest {
    public CustomSynchronizedThreadSafeMapSample() {
        super(new CustomSynchronizedThreadSafeMap<>());
    }
}
