package com.mks.dpl;

public interface ClassLoaderHolder {

	void setClassLoader(ClassLoader classloader);

	ClassLoader getClassLoader();
}
