package com.mks.cache;

/**
 * Cache node. 
 * 
 */
public interface Node {

	public String getIp();

	public int getPort();

	public int getWeight();

	public String getDesc();

}
