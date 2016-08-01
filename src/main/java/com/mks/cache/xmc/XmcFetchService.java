package com.mks.cache.xmc;

import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mks.cache.DataExtractor;
import com.mks.cache.FetchService;
import com.mks.cache.KeyGenerator;
import com.mks.cache.OriginalClientAware;
import com.mks.utils.Null;
import com.mks.utils.Nullable;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

public class XmcFetchService implements FetchService, OriginalClientAware {

	private static final Logger LOGGER = LoggerFactory.getLogger(XmcFetchService.class);
	private static final int DEFAULT_NULL_EXP = 12 * 60 * 60;

	private MemcachedClient memcachedClient;
	private int nullObjectExpireTime = DEFAULT_NULL_EXP;

	@Override
	public <T> T fetch(KeyGenerator keyGenerator, DataExtractor dataExtractor, Class<T> clazz) {
		return fetch(keyGenerator, dataExtractor, clazz, 0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T fetch(KeyGenerator keyGenerator, DataExtractor dataExtractor, Class<T> clazz,
			int exp) {
		if (null == keyGenerator)
			throw new NullPointerException("keyGenerator");

		String key = keyGenerator.generateKey();
		Object fromCache = null;
		try {
			fromCache = memcachedClient.get(key);
		} catch (TimeoutException e) {
			// TODO Implements retry mechanism in the future,default
			// 1000ms.
			LOGGER.error("memcachedClient.get:", e);
			return (T) getFromStore(dataExtractor, key, exp);
		} catch (InterruptedException e) {
			LOGGER.error("memcachedClient.get:", e);
			return (T) getFromStore(dataExtractor, key, exp);
		} catch (MemcachedException e) {
			LOGGER.error("memcachedClient.get:", e);
			return (T) getFromStore(dataExtractor, key, exp);
		}

		if (null != fromCache) {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("get data from memcached for key[" + key + "]");
			return (T) returnFromCache(fromCache);
		}

		if (null == dataExtractor) {
			if (LOGGER.isWarnEnabled())
				LOGGER.warn("get data from memcached is null,but dataExtractor also is null,is correct?");
			return (T) returnFromCache(fromCache);
		}

		return (T) getFromStore(dataExtractor, key, exp);
	}

	protected Object returnFromStore(Object fromStoreValue) {
		return fromStoreValue;
	}

	protected Object returnFromCache(Object fromCacheValue) {
		return fromCacheValue;
	}

	private Object getFromStore(DataExtractor dataExtractor, String key, int exp) {
		Object fromStore = dataExtractor.extract();
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("get data from dataExtractor for key [" + key + "],!NOT! from memcached.");

		if (null != fromStore) {

			exp = adjustExpTimeIfNull(exp, fromStore);

			try {
				memcachedClient.set(key, exp, fromStore);
			} catch (TimeoutException e) {
				LOGGER.error("memcachedClient.set", e);
			} catch (InterruptedException e) {
				LOGGER.error("memcachedClient.set", e);
			} catch (MemcachedException e) {
				LOGGER.error("memcachedClient.set", e);
			}
		}
		return returnFromStore(fromStore);
	}

	private int adjustExpTimeIfNull(int exp, Object fromStore) {
		boolean isNull = false;
		if (fromStore instanceof Null) {
			isNull = true;
		} else if (fromStore instanceof Nullable) {
			if (((Nullable) fromStore).isNull()) {
				isNull = true;
			}
		}

		int ret = exp;
		if (isNull) {
			ret = (exp > nullObjectExpireTime) ? nullObjectExpireTime : (exp / 2);
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("Null object,fromStore object:?,expire time:?", fromStore,
						nullObjectExpireTime);
		}
		return ret;
	}

	@Override
	public Object getOriginalClient() {
		return memcachedClient;
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

	int getNullObjectExpireTime() {
		return nullObjectExpireTime;
	}

	void setNullObjectExpireTime(int nullObjectExpireTime) {
		if (nullObjectExpireTime < 0)
			throw new IllegalArgumentException("nullObjectExpireTime:" + nullObjectExpireTime);
		this.nullObjectExpireTime = nullObjectExpireTime;
	}
}
