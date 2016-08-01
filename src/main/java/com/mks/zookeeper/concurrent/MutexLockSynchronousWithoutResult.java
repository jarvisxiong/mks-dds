package com.mks.zookeeper.concurrent;

import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.zookeeper.common.PathUtils;

import com.mks.zookeeper.ZkClientHolder;

/**
 * 基于普通互斥锁的方式实现同步 
 */
public class MutexLockSynchronousWithoutResult extends ConcurrentSupport implements SynchronousWithoutResult {

    public MutexLockSynchronousWithoutResult(ZkClientHolder zkClientHolder, boolean splitIfNeeded,
            boolean deleteIfNeeded) {
        super(zkClientHolder, splitIfNeeded, deleteIfNeeded);
    }

    public MutexLockSynchronousWithoutResult(ZkClientHolder zkClientHolder) {
        super(zkClientHolder);
    }

    @Override
    public void execute(String path, SynchronousProcessorWithoutResult processor) {
        PathUtils.validatePath(path);
        InterProcessLock lock = new InterProcessMutex(zkClientHolder.get(), path);
        try {
            lock.acquire();
            if (null != processor)
                processor.process();
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
    }

    @Override
    public void execute(String zkPath, String primaryKey, SynchronousProcessorWithoutResult processor) {
        String path = getPath(zkPath, primaryKey);
        execute(path, processor);
    }
}
