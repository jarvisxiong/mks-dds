package com.mks.cluster.node;

public interface DataChangeListener {

	public void onDataChanged(String path, byte[] content);
}
