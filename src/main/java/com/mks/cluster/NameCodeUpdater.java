package com.mks.cluster;

import java.util.Set;

import com.mks.cluster.node.Updatable;
import com.mks.sap.namecode.MutableNameCodeMapper;
import com.mks.sap.namecode.NameCodePair;


public class NameCodeUpdater implements Updatable<Set<NameCodePair>> {

	private MutableNameCodeMapper nameCodeMapper;

	@Override
	public void update(Set<NameCodePair> data) {
		nameCodeMapper.update(data);
	}

	public void setNameCodeMapper(MutableNameCodeMapper nameCodeMapper) {
		this.nameCodeMapper = nameCodeMapper;
	}
}
