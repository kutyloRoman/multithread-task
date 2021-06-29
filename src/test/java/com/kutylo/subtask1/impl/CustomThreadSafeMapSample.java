package com.kutylo.subtask1.impl;


import com.kutylo.subtask1.CustomThreadSafeMap;
import com.kutylo.subtask1.DasExperimentTest;

public class CustomThreadSafeMapSample extends DasExperimentTest {
    public CustomThreadSafeMapSample() {
        super(new CustomThreadSafeMap<>());
    }
}
