package com.mks.transport;

import com.mks.utils.Closure;

public interface CallbackSender extends Sender {

	void setCallback(Closure closure);
}
