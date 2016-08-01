package com.mks.zookeeper.addrs;


/**
 * 地址读取器
 * 
 * @see com.mks.zookeeper.addrs.url.UrlAddressReader 
 */
public interface AddressReader {

    /**
     * 读取地址
     * 
     * @return 正确有效的地址列表
     * @throws ReaderException 如果发生内部错误或获取到的地址无效则会抛出该异常
     */
    String read() throws ReaderException;
}
