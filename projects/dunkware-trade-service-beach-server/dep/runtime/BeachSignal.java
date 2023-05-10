package com.dunkware.trade.service.beach.server.runtime;

import java.time.LocalDateTime;

public interface BeachSignal {

	String getSignalId();
	
	String getSignalIdent();
	
	String getEntityId();
	
	String getEntityIdent();
	
	LocalDateTime getTimestamp();
}
