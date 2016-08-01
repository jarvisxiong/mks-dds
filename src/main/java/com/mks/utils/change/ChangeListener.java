package com.mks.utils.change;

import java.util.EventListener;

public interface ChangeListener<S> extends EventListener {

	void onChanged(ChangeEvent<S> event);
}
