package com.mks.transport;

import com.mks.utils.Closure;

public interface Receiver {

	void init();

	void destroy();

	void setReactor(Closure reactor);
}
