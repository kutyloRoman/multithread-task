package com.kutylo.subtask1.impl;

import com.kutylo.subtask1.DasExperimentTest;

import java.util.Collections;
import java.util.HashMap;

public class SynchronizedHashMapSample extends DasExperimentTest {
  public SynchronizedHashMapSample() {
    super(Collections.synchronizedMap(new HashMap<>()));
  }
}
