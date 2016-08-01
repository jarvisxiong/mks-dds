package com.mks.distributed.notify;

public interface Notifier {

	void notifyPathUpdate(String path, byte[] message);

	void notifyPathAdded(String path, byte[] message);

	void notifyPathRemoved(String path);
}
