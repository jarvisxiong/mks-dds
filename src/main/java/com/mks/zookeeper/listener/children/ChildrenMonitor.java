package com.mks.zookeeper.listener.children;

import org.apache.curator.framework.CuratorFramework;
 
public interface ChildrenMonitor {

    void addListener(ChildrenChangeListener listener);

    CuratorFramework getCuratorClient();
}
