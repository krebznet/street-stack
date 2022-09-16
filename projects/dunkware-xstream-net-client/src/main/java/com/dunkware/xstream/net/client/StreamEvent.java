package com.dunkware.xstream.net.client;

import java.time.LocalDateTime;

public interface StreamEvent {
	
	StreamEntity getEntity();
	
	String getIdent();
	
	int getId();
	
	LocalDateTime getTimestamp();

}
