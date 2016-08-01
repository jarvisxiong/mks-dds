package com.mks.transport.failover.jmx;

 
public interface SenderClusterMBean {

    public long getSendMessages();

    public String getSenders();

    public void setSenderLocator(String key);

    public String getSenderLocator();
}
