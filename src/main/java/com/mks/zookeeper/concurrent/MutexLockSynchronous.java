package com.mks.zookeeper.concurrent;

import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.zookeeper.common.PathUtils;

import com.mks.zookeeper.ZkClientHolder;


/**
 * 基于普通互斥锁的方式实现同步 
 */
public class MutexLockSynchronous extends ConcurrentSupport implements Synchronous {

    public MutexLockSynchronous(ZkClientHolder zkClientHolder, boolean splitIfNeeded, boolean deleteIfNeeded) {
        super(zkClientHolder, splitIfNeeded, deleteIfNeeded);
    }

    public MutexLockSynchronous(ZkClientHolder zkClientHolder) {
        super(zkClientHolder);
    }

    @Override
    public <T> T execute(String path, SynchronousProcessor<T> processor) {
        PathUtils.validatePath(path);
        InterProcessLock lock = new InterProcessMutex(zkClientHolder.get(), path);
        try {
            lock.acquire();
            if (null != processor)
                return processor.process();
        }
        catch (CanCaughtException e) {
            if (null != processor)
                processor.exceptionCaught(e);
        }
        catch (CanNotCaughtException e) {
            throw e;
        }
        catch (Exception e) {
            throw new SynchronousException(e);
        }
        finally {
            try {
                lock.release();
            }
            catch (Exception e) {
            }
            deleteIfNeeded(path);
        }
        return null;
    }

    @Override
    public <T> T execute(String zkPath, String primaryKey, SynchronousProcessor<T> processor) {
        String path = getPath(zkPath, primaryKey);
        return execute(path, processor);
    }
}
