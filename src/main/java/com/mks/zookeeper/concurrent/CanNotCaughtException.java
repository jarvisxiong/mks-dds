package com.mks.zookeeper.concurrent;



/**
 * <p>不允许捕获的异常</p>
 * 使用场景：一般在同一个事务中可造成影响的可抛出这个异常。
 * <pre>
 * 
synchronousWithoutResult.execute("/zk/synchronous", new SynchronousProcessorWithoutResult() {

    public void process() throws Exception {
        // 插入一条数据到数据库
        if (dao.insert(1) <= 0) {
            throw new CanNotCaughtException();
        }
        if (true) {
            throw new CanNotCaughtException(); // 不会捕获这个异常
            // throw new CanCaughtException(); // 会捕获这个异常，并执行 exceptionCaught 方法
        }
    }

    public void exceptionCaught(Throwable throwable) {
        LOGGER.error("exceptionCaught:", throwable);
    }
});
 * </pre>
 * 
 * @author Allen Hu 
 * 2015-6-3
 */
public class CanNotCaughtException extends RuntimeException {

    private static final long serialVersionUID = -4976578464768376868L;

    /**
     * 
     */
    public CanNotCaughtException() {
    }

    /**
     * @param message
     */
    public CanNotCaughtException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public CanNotCaughtException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public CanNotCaughtException(String message, Throwable cause) {
        super(message, cause);
    }

}
