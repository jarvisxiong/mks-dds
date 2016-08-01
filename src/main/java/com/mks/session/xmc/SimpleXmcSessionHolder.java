package com.mks.session.xmc;

import com.mks.session.Session;
import com.mks.session.SessionHolder;
import com.mks.utils.StringHelper;

import net.rubyeye.xmemcached.MemcachedClient;

public class SimpleXmcSessionHolder extends DefaultXmcSessionHolder {

    private static final long serialVersionUID = 5614390966799316810L;

    public SimpleXmcSessionHolder(MemcachedClient memClient, int expire) {
		super(memClient, expire, null);
	}
	
	public SimpleXmcSessionHolder(MemcachedClient memClient, int expire, SessionHolder sessionHolder) {
        super(memClient, expire, sessionHolder);
    }

    @Override
	protected Session createSession() {
		return new SimpleXmcSession(StringHelper.uuid(), this);
	}
}
