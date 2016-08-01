package com.mks.cache;


public interface KeyKeeper {

    int keep(String key, int type);
    
    int count(String key, int type);
}
