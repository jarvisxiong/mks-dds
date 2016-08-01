package com.mks.distributed.jmx;

public interface EndpointRegistrarMBean {

	public String getDesc();

	public void setWeights(int weights);

	public int getWeights();
}
