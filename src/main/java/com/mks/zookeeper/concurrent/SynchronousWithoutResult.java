package com.mks.zookeeper.concurrent;


/**
 * 没有返回值的同步任务处理，支持分布式
 * <p>
 * 如果想希望通过Spring-AOP来实现，那么请参考{@linkplain org.bigmouth.nvwa.zookeeper.concurrent.spring.SynchronousAdvisor SychronousAdvisor}实现。
 * </p> 
 * @see com.mks.zookeeper.concurrent.Synchronous
 */
public interface SynchronousWithoutResult {

    /**
     * 同步执行，根据<code>path</code>标识来区分同步工作。不同的<code>path</code>将不会同步进行。
     * 
     * @param <T> 处理结果类型
     * @param path 任务节点
     * <pre>
     * e.g. "/project/synchronous/0000001"
     * </pre>
     * @param processor 业务处理器
     * @throws org.bigmouth.nvwa.zookeeper.concurrent.CanNotCaughtException
     * @throws org.bigmouth.nvwa.zookeeper.concurrent.SynchronousException
     */
    @Deprecated
    void execute(String path, SynchronousProcessorWithoutResult processor);
    
    /**
     * 同步执行，根据<code>primaryKey</code>标识来区分同步工作。不同的<code>primaryKey</code>将不会同步进行。
     * 
     * @param <T> 处理结果类型
     * @param zkPath 任务根节点
     * <pre>
     * e.g. "/project/synchronous"
     * </pre>
     * @param primaryKey 任务编号
     * <pre>
     * e.g. "0000001"
     * </pre>
     * @param processor 业务处理器
     * @throws org.bigmouth.nvwa.zookeeper.concurrent.CanNotCaughtException
     * @throws org.bigmouth.nvwa.zookeeper.concurrent.SynchronousException
     */
    void execute(String zkPath, String primaryKey, SynchronousProcessorWithoutResult processor);
}
