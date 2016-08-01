package com.mks.dpl.event.listener;

import com.mks.dpl.ClassLoaderHolder;
import com.mks.dpl.event.ActivedEvent;
import com.mks.dpl.event.ConfigChangedEvent;
import com.mks.dpl.event.CreatedEvent;
import com.mks.dpl.event.DeActivedEvent;
import com.mks.dpl.event.DestroyedEvent;
import com.mks.dpl.event.StatusChangedEvent;

public class ReviseContextClassLoaderListener<T extends LifeCycleListener> extends
		ReviseContextClassLoaderSupport implements LifeCycleListener {

	private final T listener;

	public ReviseContextClassLoaderListener(ClassLoaderHolder classLoaderHolder, T listener) {
		super(classLoaderHolder);
		if (null == listener)
			throw new NullPointerException("listener");
		if (null == classLoaderHolder)
			throw new NullPointerException("classLoaderHolder");
		this.listener = listener;
	}

	public T getListener() {
		return listener;
	}

	@Override
	public void init() {
		revise(new ReviseCallback() {
			@Override
			public void execute() {
				getListener().init();
			}
		});
	}

	@Override
	public void destroy() {
		revise(new ReviseCallback() {
			@Override
			public void execute() {
				getListener().destroy();
			}
		});
	}

	@Override
	public void onCreated(final CreatedEvent event) {
		revise(new ReviseCallback() {
			@Override
			public void execute() {
				getListener().onCreated(event);
			}
		});
	}

	@Override
	public void onDestroyed(final DestroyedEvent event) {
		revise(new ReviseCallback() {
			@Override
			public void execute() {
				getListener().onDestroyed(event);
			}
		});
	}

	@Override
	public void onActived(final ActivedEvent event) {
		revise(new ReviseCallback() {
			@Override
			public void execute() {
				getListener().onActived(event);
			}
		});
	}

	@Override
	public void onDeActived(final DeActivedEvent event) {
		revise(new ReviseCallback() {
			@Override
			public void execute() {
				getListener().onDeActived(event);
			}
		});
	}

	@Override
	public void onStatusChanged(final StatusChangedEvent event) {
		revise(new ReviseCallback() {
			@Override
			public void execute() {
				getListener().onStatusChanged(event);
			}
		});
	}

	@Override
	public void onConfigChanged(final ConfigChangedEvent event) {
		revise(new ReviseCallback() {
			@Override
			public void execute() {
				getListener().onConfigChanged(event);
			}
		});
	}
}
