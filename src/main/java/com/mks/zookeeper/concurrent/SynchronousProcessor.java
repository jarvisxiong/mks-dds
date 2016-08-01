package com.mks.zookeeper.concurrent;


/**
 * 有返回值的同步业务处理器 
 */
public interface SynchronousProcessor<T> {

    /**
     * 处理具体的业务
     * 
     * @return
     */
    T process() throws Exception;
    
    /**
     * 异常捕获，只会捕获抛出允许捕获的异常。
     * 
     * @param throwable
     * @see org.bigmouth.nvwa.zookeeper.concurrent.CanCaughtException
     */
    void exceptionCaught(Throwable throwable);
}
