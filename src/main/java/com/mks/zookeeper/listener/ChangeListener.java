package com.mks.zookeeper.listener;

import java.util.EventListener;

import com.mks.zookeeper.listener.children.ChildrenMonitor;
 
public interface ChangeListener<E> extends EventListener {

    public void onChanged(ChildrenMonitor client, E event);
}
