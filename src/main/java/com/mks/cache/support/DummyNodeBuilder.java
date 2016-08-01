package com.mks.cache.support;

import java.util.ArrayList;
import java.util.List;

import com.mks.cache.Node;
import com.mks.cache.NodeBuilder;


public class DummyNodeBuilder implements NodeBuilder {

	@Override
	public List<Node> build() {
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(new DefaultNode("localhost", 11211));
		nodes.add(new DefaultNode("localhost", 11212));
		return nodes;
	}

}
