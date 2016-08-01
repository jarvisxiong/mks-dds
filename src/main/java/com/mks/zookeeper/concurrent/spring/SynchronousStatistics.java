package com.mks.zookeeper.concurrent.spring;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.google.common.collect.Maps;

public class SynchronousStatistics {

    private Map<String, AtomicLong> wait = Maps.newConcurrentMap();

    public void increment(String path) {
        AtomicLong atomicLong = newIfNotExists(path);
        atomicLong.incrementAndGet();
    }

    public void decrement(String path) {
        AtomicLong atomicLong = newIfNotExists(path);
        atomicLong.decrementAndGet();
    }
    
    private AtomicLong newIfNotExists(String path) {
        AtomicLong atomicLong = wait.get(path);
        if (null == atomicLong) {
            wait.put(path, new AtomicLong(0));
        }
        return atomicLong;
    }

    public Map<String, AtomicLong> getWait() {
        return wait;
    }

    public void setWait(Map<String, AtomicLong> wait) {
        this.wait = wait;
    }
}
