package com.mks.cache.xmc;

import java.io.IOException;
import java.util.List;

import com.mks.cache.CacheServiceException;
import com.mks.cache.MemClientBuilder;
import com.mks.cache.Node;
import com.mks.cache.NodeBuilder;

import net.rubyeye.xmemcached.MemcachedClient;


public class XmcMemClientBuilder implements MemClientBuilder {

	private NodeBuilder nodeBuilder;

	private MemcachedClient memcachedClient;

	@Override
	public Object build() {
		if (null == nodeBuilder)
			throw new NullPointerException("nodeBuilder");

		List<Node> nodes = nodeBuilder.build();
		if (null == nodes)
			throw new NullPointerException("nodes");
		if (0 == nodes.size())
			throw new CacheServiceException("nodes size is 0.");

		try {
			for (Node n : nodes)
				memcachedClient.addServer(n.getIp(), n.getPort(), n.getWeight());
		} catch (IOException e) {
			throw new CacheServiceException("build:", e);
		}
		return memcachedClient;
	}

	public void setNodeBuilder(NodeBuilder nodeBuilder) {
		this.nodeBuilder = nodeBuilder;
	}

	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

}
