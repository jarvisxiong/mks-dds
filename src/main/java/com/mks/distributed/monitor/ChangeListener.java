package com.mks.distributed.monitor;

import java.util.EventListener;

public interface ChangeListener<E> extends EventListener {

	public void onChanged(E event);
}
