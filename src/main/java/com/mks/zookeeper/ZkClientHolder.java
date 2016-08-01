package com.mks.zookeeper;

import org.apache.commons.lang.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.mks.utils.BaseLifeCycleSupport;
import com.mks.zookeeper.addrs.ReaderFactory;


/**
 * ZooKeeper client holder
 * 
 * <pre>
 * &lt;bean class="org.bigmouth.nvwa.zookeeper.ZkClientHolder" id="zkClientHolder" 
 *  lazy-init="false" init-method="init" destroy-method="destroy"&gt;
 *  &lt;constructor-arg value="172.16.3.24:2181" /&gt;
 *  &lt;constructor-arg value="60000" /&gt;
 * &lt;/bean&gt;
 * </pre>
 * @author Allen Hu 
 * 2015-4-16
 */
public class ZkClientHolder extends BaseLifeCycleSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZkClientHolder.class);
    
    public static final int MAX_RETRIES = 3;
    public static final int BASE_SLEEP_TIMEMS = 3000;
    public static final int SESSION_TIMEOUT = 60000;

    private CuratorFramework zkClient;

    private final String connectString;
    private final int sessionTimeout;
    
    public ZkClientHolder(String connectString) {
        this(connectString, SESSION_TIMEOUT);
    }

    public ZkClientHolder(String connectString, int sessionTimeout) {
        String realaddrs = ReaderFactory.matching(connectString).read();
        Preconditions.checkArgument(StringUtils.isNotBlank(realaddrs), "connectString cannot be blank");
        Preconditions.checkArgument(sessionTimeout >= 10000, "sessionTimeout must be greater than 10000");
        this.connectString = realaddrs;
        this.sessionTimeout = sessionTimeout;
    }
    
    public CuratorFramework get() {
        return zkClient;
    }

    @Override
    protected void doInit() {
        zkClient = CuratorFrameworkFactory.builder()
                .sessionTimeoutMs(sessionTimeout)
                .connectString(connectString)
                .retryPolicy(new ExponentialBackoffRetry(BASE_SLEEP_TIMEMS, MAX_RETRIES))
                .build();
        zkClient.start();
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Connected to ZooKepper server: {}", connectString);
        }
    }

    @Override
    protected void doDestroy() {
        if (null != zkClient)
            zkClient.close();
    }
}
