package com.mks.sap.namecode;

import java.util.Set;

public interface MutableNameCodeMapper extends NameCodeMapper {

	void update(Set<NameCodePair> slist);
}
