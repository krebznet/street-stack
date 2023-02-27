package com.dunkware.trade.service.beach.server.runtime;

import com.dunkware.xstream.model.signal.StreamSignalListener;
import com.dunkware.xstream.model.spec.StreamSpec;

public interface BeachStream {

	public void addSignalListener(StreamSignalListener listener, String...signals) throws Exception;
	
	public void removeSignalListener(StreamSignalListener listener);
	
	public boolean signalExists(String signal); 
	
	public StreamSpec getSpec();
}
