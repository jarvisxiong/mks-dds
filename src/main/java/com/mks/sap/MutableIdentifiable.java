package com.mks.sap;

import java.util.UUID;

public interface MutableIdentifiable extends Identifiable {

	void setIdentification(UUID id);
}
