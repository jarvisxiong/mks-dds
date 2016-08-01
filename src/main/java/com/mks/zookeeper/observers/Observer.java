package com.mks.zookeeper.observers;


/**
 * 观察者 
 */
public interface Observer<T> {

    void add(T message);
    
    void update(T message);
    
    void remove(T message);
    
    Class<T> getClassType();
    
    String getObserverName();
}
