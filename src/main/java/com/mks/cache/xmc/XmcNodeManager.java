package com.mks.cache.xmc;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.mks.cache.CacheServiceException;
import com.mks.cache.Node;
import com.mks.cache.NodeManager;
import com.mks.cache.OriginalClientAware;
import com.mks.cache.support.DefaultNode;

import net.rubyeye.xmemcached.MemcachedClient;

/**
 * Using xmc.  
 */
public class XmcNodeManager implements NodeManager, OriginalClientAware {

	private MemcachedClient memcachedClient;

	@Override
	public void registerNode(Node cacheNode) {
		if (null == cacheNode)
			throw new NullPointerException("cacheNode");
		try {
			memcachedClient.addServer(cacheNode.getDesc(), cacheNode.getWeight());
		} catch (IOException e) {
			throw new CacheServiceException("registerNode:", e);
		}
	}

	@Override
	public void unregisterNode(Node cacheNode) {
		if (null == cacheNode)
			throw new NullPointerException("cacheNode");
		memcachedClient.removeServer(cacheNode.getDesc());
	}

	@Override
	public Object getOriginalClient() {
		return memcachedClient;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Node> getAvaliableNodes() {
		Collection<InetSocketAddress> inets = memcachedClient.getAvaliableServers();
		List<Node> nodes = new ArrayList<Node>();
		for (Iterator<InetSocketAddress> it = inets.iterator(); it.hasNext();) {
			InetSocketAddress inet = it.next();
			String ip = inet.getAddress().getHostAddress();
			int port = inet.getPort();

			nodes.add(new DefaultNode(ip, port, -1));
		}
		return Collections.unmodifiableList(nodes);
	}

	@Override
	public void updateNodeWeight(Node cacheNode, int weight) {
		// TODO Auto-generated method stub
	}

	public void setMemcachedClient(Object memcachedClient) {
		if (null == memcachedClient)
			throw new NullPointerException();
		if (!(memcachedClient instanceof MemcachedClient))
			throw new IllegalArgumentException(
					"Illegal passed parameter,expect[MemcachedClient],but["
							+ memcachedClient.getClass() + "]");

		this.memcachedClient = (MemcachedClient) memcachedClient;
	}

}
