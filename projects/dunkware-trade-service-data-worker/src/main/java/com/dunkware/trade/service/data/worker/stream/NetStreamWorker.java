package com.dunkware.trade.service.data.worker.stream;

import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GEntitySnapshot;

public interface NetStreamWorker {

	public void entitySnapshot(GEntitySnapshot snapshot);
	
	public void entitySignal(GEntitySignal signal);
	
	public void sessionStopped();
	
	public void sessionStarted();
	
	// scannerRequest --> 
	
	// scannerResponse --> 
}
