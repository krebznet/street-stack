package com.dunkware.trade.service.data.util.cache.session;

import java.util.List;

import com.dunkware.net.proto.stream.GEntitySignal;

public interface SessionEntitySignalType {
	
	String getSignalIdentifier();
	
	List<GEntitySignal> getSignals();
	
	

}
