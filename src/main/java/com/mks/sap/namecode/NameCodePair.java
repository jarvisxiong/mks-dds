package com.mks.sap.namecode;

public interface NameCodePair extends PlugInServiceNamePair, PlugInServiceCodePair {

	@Override
	String getPlugInName();

	@Override
	short getPlugInCode();

	@Override
	String getServiceName();

	@Override
	short getServiceCode();
}
