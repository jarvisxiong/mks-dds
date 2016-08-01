package com.mks.session;

public interface MutableSession extends Session {

	void update(long time);

	void setHolder(SessionHolder holder);
}
