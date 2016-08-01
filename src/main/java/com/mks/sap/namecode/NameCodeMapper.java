package com.mks.sap.namecode;

public interface NameCodeMapper {

	PlugInServiceNamePair getNameOf(PlugInServiceCodePair cs) throws NoSuchNameCodeMappingException;

	PlugInServiceCodePair getCodeOf(PlugInServiceNamePair ns) throws NoSuchNameCodeMappingException;
}
