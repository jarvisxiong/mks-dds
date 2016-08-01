package com.mks.cache;

/**
 * The way to obtain original cache client which implement the function of
 * interacting with cache server.
 *  
 */
public interface OriginalClientAware {

	public Object getOriginalClient();

}
