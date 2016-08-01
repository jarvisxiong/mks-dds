package com.mks.zookeeper.concurrent;

import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.KeeperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mks.zookeeper.ZkClientHolder;
import com.mks.zookeeper.utils.ZkPathUtils;


public class ConcurrentSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConcurrentSupport.class);
    protected final ZkClientHolder zkClientHolder;
    /** 任务节点是否需要进行分组 */
    private boolean splitIfNeeded = false;
    /** 释放锁之后是否需要删除Lock节点 */
    private boolean deleteIfNeeded = true;
    
    public ConcurrentSupport(ZkClientHolder zkClientHolder) {
        this.zkClientHolder = zkClientHolder;
    }

    public ConcurrentSupport(ZkClientHolder zkClientHolder, boolean splitIfNeeded, boolean deleteIfNeeded) {
        super();
        this.zkClientHolder = zkClientHolder;
        this.splitIfNeeded = splitIfNeeded;
        this.deleteIfNeeded = deleteIfNeeded;
    }

    protected void deleteIfNeeded(String path) {
        if (isDeleteIfNeeded()) {
            try {
                zkClientHolder.get().delete().guaranteed().inBackground().forPath(path);
            }
            catch (KeeperException.NotEmptyException e) {
                // ignore
            }
            catch (KeeperException.NoNodeException e) {
                // ignore - already deleted (possibly expired session, etc.)
            }
            catch (Exception e) {
                LOGGER.error("", e);
            }
        }
    }
    
    protected String getPath(String zkPath, String primaryKey) {
        String child = splitIfNeeded ? ZkPathUtils.group(primaryKey) : primaryKey;
        return ZKPaths.makePath(zkPath, child);
    }
    
    public boolean isSplitIfNeeded() {
        return splitIfNeeded;
    }
    
    public void setSplitIfNeeded(boolean splitIfNeeded) {
        this.splitIfNeeded = splitIfNeeded;
    }
    
    public boolean isDeleteIfNeeded() {
        return deleteIfNeeded;
    }
    
    public void setDeleteIfNeeded(boolean deleteIfNeeded) {
        this.deleteIfNeeded = deleteIfNeeded;
    }
}
