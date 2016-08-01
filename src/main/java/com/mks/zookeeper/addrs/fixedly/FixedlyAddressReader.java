package com.mks.zookeeper.addrs.fixedly;

import com.mks.zookeeper.addrs.AddressReader;
import com.mks.zookeeper.addrs.ReaderException;


/**
 * 不变的读取器，传入的值是什么就返回什么 
 */
public class FixedlyAddressReader implements AddressReader {

    private final String value;
    
    public FixedlyAddressReader(String value) {
        this.value = value;
    }

    @Override
    public String read() throws ReaderException {
        return value;
    }
}
