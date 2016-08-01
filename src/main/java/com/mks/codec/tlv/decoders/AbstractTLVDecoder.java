package com.mks.codec.tlv.decoders;

import com.mks.codec.error.IllegalTLVContentHandler;
import com.mks.codec.tlv.TLVDecoder;


public abstract class AbstractTLVDecoder<R extends Object> implements TLVDecoder<R> {
    
    protected IllegalTLVContentHandler illegalTLVContentHandler;

    @Override
    public void setIllegalTLVContentHandler(IllegalTLVContentHandler illegalTLVContentHandler) {
        this.illegalTLVContentHandler = illegalTLVContentHandler;
    }
}
