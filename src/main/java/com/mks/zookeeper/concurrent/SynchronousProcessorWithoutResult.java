package com.mks.zookeeper.concurrent;


/**
 * 无返回值的同步业务处理器 
 */
public interface SynchronousProcessorWithoutResult {

    /**
     * 处理具体的业务 
     */
    void process() throws Exception;
    
    /**
     * 异常捕获，只会捕获抛出允许捕获的异常。
     * 
     * @param throwable
     * @see org.bigmouth.nvwa.zookeeper.concurrent.CanCaughtException
     */
    void exceptionCaught(Throwable throwable);
}
