package com.mks.sap;

import java.util.List;

public interface SapResponse extends SapTransactionMessage {

	SapResponseStatus getStatus();

	boolean existsExtendedItems();

	List<ExtendedItem> getExtendedItems();

	int getExtendedItemsCount();
}
