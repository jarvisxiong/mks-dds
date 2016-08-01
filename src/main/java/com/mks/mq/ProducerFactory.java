package com.mks.mq;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;


public class ProducerFactory {

    private static final Map<Type, Producer> PRODUCERS = Maps.newConcurrentMap();
    
    public ProducerFactory(Map<Type, Producer> producers) {
        PRODUCERS.putAll(producers);
    }

    public static Producer getFirst() {
        for (Entry<Type, Producer> e : PRODUCERS.entrySet()) {
            return e.getValue();
        }
        return null;
    }
    
    public static Producer getProducer(Type type) {
        return PRODUCERS.get(type);
    }
    
    public static Producer getAlionsProducer() {
        return PRODUCERS.get(Type.ALIONS);
    }
    
    public static Producer getRocketMQProducer() {
        return PRODUCERS.get(Type.ROCKETMQ);
    }
    
    public static Producer getActiveMQProducer() {
        return PRODUCERS.get(Type.ACTIVEMQ);
    }
}
