package com.mks.zookeeper.listener;

import com.mks.zookeeper.listener.children.ChildrenMonitor;



public interface Change {

    void add(ChildrenMonitor monitor, String path, byte[] data);
    
    void update(ChildrenMonitor monitor, String path, byte[] data);
    
    void remove(ChildrenMonitor monitor, String path, byte[] data);
}
