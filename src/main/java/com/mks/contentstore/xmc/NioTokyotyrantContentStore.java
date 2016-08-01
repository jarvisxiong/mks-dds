package com.mks.contentstore.xmc;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mks.contentstore.ContentStore;
import com.mks.contentstore.impl.ContentStoreUtils;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;


public class NioTokyotyrantContentStore implements ContentStore {

	private static final Logger LOGGER = LoggerFactory.getLogger(NioTokyotyrantContentStore.class);

	private MemcachedClient memcachedClient;

	@Override
	public boolean contains(String key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public byte[] fetch(String key) {
		try {
			return (byte[]) memcachedClient.get(key);
		} catch (TimeoutException e) {
			LOGGER.error("memcachedClient.get:", e);
			return null;
		} catch (InterruptedException e) {
			LOGGER.error("memcachedClient.get:", e);
			return null;
		} catch (MemcachedException e) {
			LOGGER.error("memcachedClient.get:", e);
			return null;
		}
	}

	@Override
	public List<String> getKeys() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(String key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String store(byte[] content) {
		String key = ContentStoreUtils.content2key(content);
		boolean ret = false;
		try {
			ret = memcachedClient.add(key, 0, content);
		} catch (TimeoutException e) {
			LOGGER.error("memcachedClient.add:", e);
			return null;
		} catch (InterruptedException e) {
			LOGGER.error("memcachedClient.add:", e);
			return null;
		} catch (MemcachedException e) {
			LOGGER.error("memcachedClient.add:", e);
			return null;
		}
		if (ret) {
			return key;
		} else {
			byte[] old = null;
			try {
				old = (byte[]) memcachedClient.get(key);
			} catch (TimeoutException e) {
				LOGGER.error("memcachedClient.get:", e);
				return null;
			} catch (InterruptedException e) {
				LOGGER.error("memcachedClient.get:", e);
				return null;
			} catch (MemcachedException e) {
				LOGGER.error("memcachedClient.get:", e);
				return null;
			}
			if (Arrays.equals(content, old)) {
				LOGGER.info("content for key [" + key + "] already exist. just ingore");
				return key;
			} else {
				LOGGER.error("key [" + key
						+ "] already exist. but content mismatch, maybe internal error?");
			}
			return null;
		}
	}

	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

}
