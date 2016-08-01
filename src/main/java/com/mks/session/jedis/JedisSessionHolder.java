package com.mks.session.jedis;

import com.mks.session.Session;
import com.mks.session.SessionHolder;
import com.mks.session.Trackable;


public class JedisSessionHolder implements SessionHolder {

    @Override
    public Session get(Trackable trackable) {
        return null;
    }

    @Override
    public Session getSession(Trackable trackable) {
        return null;
    }

    @Override
    public void put(Session session) {
    }

    @Override
    public void remove(Trackable trackable) {
    }
}
