package com.dunkware.trade.service.stream.server.meta;

import java.util.List;

public interface MetaStream {

	// version -- getV
	
	int getRevision(); 
	
	List<MetaStreamSignal> getSignals();
	
	List<MetaStreamField> getFields();
	// MetaLayer 
	// Average of Last Price of 5 Minutes 
	// Weighted Average -> 
	// Deploy 
	// want a snapshot -> 
	
	// stream cache -> StreamCacheWorker
	//		-> cache signals -> date 
	// 8,000 
	/**
	 * it should look for signals that reference deleted fields. 
	 * and throw exception. 
	 * @param okay
	 */
	void updateScript(Object okay); 
}
