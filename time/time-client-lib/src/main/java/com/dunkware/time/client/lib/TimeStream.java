package com.dunkware.time.client.lib;

import com.dunkware.time.client.lib.stream.StreamSignalSubscription;

public interface TimeStream {
	
	public boolean isRunning();
	
	public StreamSignalSubscription signalSubscription(String...signalTypes);
	

}
