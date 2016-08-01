package com.mks.cache;
 
public interface KeepFetchService extends OriginalClientAware {

    <T> T fetch(KeepKeyGenerator keyGenerator, DataExtractor dataExtractor, Class<T> clazz);

    <T> T fetch(KeepKeyGenerator keyGenerator, DataExtractor dataExtractor, Class<T> clazz, int exp);
    
    Object getOriginalClient();
}
